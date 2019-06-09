package com.zcf.world.controller.console;

import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.common.utils.FileUploadUtils;
import com.zcf.world.pojo.OrderDetails;
import com.zcf.world.service.layui.LayUiOrderDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
/**
* @author 许宝予
* @date 2019/06/06
*/
@RestController
@RequestMapping("/order/details")
public class LayUiOrderDetailsController {

    @Autowired
    private LayUiOrderDetailsService LayUiorderDetailsservice;

    @RequestMapping(value ="add",produces = {"application/json;charset=UTF-8"})
    public boolean add(@RequestBody OrderDetails orderDetails) {
        return this.LayUiorderDetailsservice.add(orderDetails);
    }

    @RequestMapping(value ="delete",produces = {"application/json;charset=UTF-8"})
    public boolean delete(@RequestParam Integer id) {
        return this.LayUiorderDetailsservice.delete(id);
    }

    @RequestMapping(value ="update",produces = {"application/json;charset=UTF-8"})
    public boolean update(@RequestBody OrderDetails orderDetails) {
        return this.LayUiorderDetailsservice.update(orderDetails);
    }

    @RequestMapping(value ="query",produces = {"application/json;charset=UTF-8"})
    public LayUiResult queryOrderDetails(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer limit) {
        return this.LayUiorderDetailsservice.query(page,limit);
    }

    @RequestMapping(value ="search",produces = {"application/json;charset=UTF-8"})
    public LayUiResult search(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer limit,
        @RequestParam String keywords) {
        return this.LayUiorderDetailsservice.search(page,limit,keywords);
    }

    @RequestMapping(value ="upload",produces = {"application/json;charset=UTF-8"})
    public Map UploadBrand(@RequestParam("file") MultipartFile file){
        return FileUploadUtils.uploadLayUiImg(file,"img/");
    }
}
