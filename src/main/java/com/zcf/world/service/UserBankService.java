package com.zcf.world.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.mapper.BankLogoMapper;
import com.zcf.world.mapper.UserBankMapper;
import com.zcf.world.pojo.BankLogo;
import com.zcf.world.pojo.UserBank;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Service
public class UserBankService{

    @Resource
    private UserBankMapper userBankmapper;
    @Resource
    private BankLogoMapper bankLogoMapper;

    /**
     * 新增一条数据
     *
     * @param
     */
    public void addUserBank(String userName,String bankAccount,String userPhone,String userAccount,String bankName,Integer userid) {
        List<UserBank> list=this.userBankmapper.queryBankByBankaccount(bankAccount);
        if (list.size()>0){
            throw new CommonException(ExceptionEnum.BANK_IS_USED);
        }
        //银行代码请求接口 url
        String url = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="+bankAccount+"&cardBinCheck=true";
        //发送请求，得到 josn 类型的字符串
        String result = HttpUtil.get(url);
        // 转为 Json 对象
        JSONObject json = new JSONObject(result);
        //获取到 bank 代码
        String bank = String.valueOf(json.get("bank"));
        BankLogo bankLogo=bankLogoMapper.queryLogoByBank(bank);
        String logo=bankLogo.getBanklogo();
        UserBank userBank=new UserBank();
        userBank.setUserName(userName);
        userBank.setBankAccount(bankAccount);
        userBank.setUserPhone(userPhone);
        userBank.setUserAccount(userAccount);
        userBank.setBankName(bankName);
        userBank.setBankLogo(logo);
        userBank.setCreatTime(new Date());
        userBank.setUserid(userid);
        int count = this.userBankmapper.insertSelective(userBank);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    public void deleteBank(Integer id) {
        Integer a=this.userBankmapper.deleteByPrimaryKey(id);
        if (a!=1){
            throw new CommonException(ExceptionEnum.DELETE_FAILURE);
        }
    }

    public List<UserBank> queryBank(Integer id, Integer userid) {
        Example example=new Example(UserBank.class);
        Example.Criteria criteria=example.createCriteria();
        if (id!=null) {
            criteria.andEqualTo("id",id);
        }
        if (userid!=null){
            criteria.andEqualTo("userid",userid);
        }
        example.setOrderByClause("creatTime DESC");
        List<UserBank> list=this.userBankmapper.selectByExample(example);
        return list;
    }
}
