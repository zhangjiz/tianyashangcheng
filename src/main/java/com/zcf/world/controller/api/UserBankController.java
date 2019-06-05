package com.zcf.world.controller.api;


import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.zcf.world.pojo.UserBank;
import com.zcf.world.service.UserBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.zcf.world.common.utils.FileUploadUtils.fileUpload;

/**
* @author xiaodong
* @date 2019/06/05
*/
@RestController
@RequestMapping("userBank")
@Api(value = "用户绑定银行卡控制器", tags = {"用户绑定银行卡接口"})
public class UserBankController {

    @Autowired
    private UserBankService userBankService;

    @ApiOperation(value = "添加绑定银行卡")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
                @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
                @ApiImplicitParam(name = "bankAccount", value = "银行卡号", dataType = "String"),
                @ApiImplicitParam(name = "userPhone", value = "用户联系方式", dataType = "String"),
                @ApiImplicitParam(name = "userAccount", value = "用户身份证", dataType = "String"),
                @ApiImplicitParam(name = "bankName", value = "开户行", dataType = "String"),
                @ApiImplicitParam(name = "userid", value = "", dataType = "Integer"),
    })
    public ResponseEntity<Void> addUserBank(String userName,String bankAccount,String userPhone,String userAccount,String bankName,Integer userid) {
        this.userBankService.addUserBank(userName,bankAccount,userPhone,userAccount,bankName,userid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除绑定银行卡")
    @PostMapping("deleteBank")
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteBank(Integer id) {
        this.userBankService.deleteBank(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "查询绑定银行卡")
    @PostMapping("queryBank")
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<List<UserBank>> queryBank(Integer id,Integer userid) {
        return ResponseEntity.ok(this.userBankService.queryBank(id,userid));
    }

    @ApiOperation(value = "上传图片")
    @PostMapping("uploadImg")
    public String uploadImg(MultipartFile file) {
        return fileUpload(file, "userPortrait/");
    }

    public static void main(String[] args) throws Exception{
        String bankNo = "6228480455261745570";
        //银行代码请求接口 url
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="+bankNo+"&cardBinCheck=true";
        //发送请求，得到 josn 类型的字符串
        String result = HttpUtil.get(url);
        // 转为 Json 对象
        JSONObject json = new JSONObject(result);
        //获取到 bank 代码
        String bank = String.valueOf(json.get("bank"));
        System.out.println(bank);
        //爬取支付宝银行合作商页面
        String listContent = HttpUtil.get("http://ab.alipay.com/i/yinhang.htm");
        //过滤得到需要的银行名称
        List<String> titles = ReUtil.findAll("<span title=\"(.*?)\" class=\"icon "+bank+"\">(.*?)</span>", listContent, 2);
        for (String title : titles) {
            //打印银行名称
            Console.log(title);
        }
    }
}
