package com.zcf.world.controller.api;


import com.zcf.world.pojo.UserAddress;
import com.zcf.world.service.UserAddressService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
* @author 许宝予
* @date 2019/06/04
*/
@RestController
@RequestMapping("userAddress")
@Api(value = "用户收货地址控制器", tags = {"用户收货地址接口"})
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "userName", value = "收货人姓名", dataType = "String"),
                @ApiImplicitParam(name = "userPhone", value = "收货人联系方式", dataType = "String"),
                @ApiImplicitParam(name = "area", value = "所在地区", dataType = "String"),
                @ApiImplicitParam(name = "detailAddress", value = "详细地址", dataType = "String"),
                @ApiImplicitParam(name = "addtime", value = "添加时间", dataType = "Date"),
                @ApiImplicitParam(name = "type", value = "是否默认（0默认1普通）", dataType = "String"),
    })
    public ResponseEntity<Void> addUserAddress(UserAddress userAddress) {
        this.userAddressService.addUserAddress(userAddress);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteUserAddressById(@PathVariable Integer id) {
        this.userAddressService.deleteUserAddressById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "收货人姓名", dataType = "String"),
            @ApiImplicitParam(name = "userPhone", value = "收货人联系方式", dataType = "String"),
            @ApiImplicitParam(name = "area", value = "所在地区", dataType = "String"),
            @ApiImplicitParam(name = "detailAddress", value = "详细地址", dataType = "String"),
            @ApiImplicitParam(name = "addtime", value = "添加时间", dataType = "Date"),
            @ApiImplicitParam(name = "type", value = "是否默认（0默认1普通）", dataType = "String"),
    })
    public ResponseEntity<Void> updateUserAddress(UserAddress userAddress) {
        this.userAddressService.updateUserAddress(userAddress);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<UserAddress> getUserAddress(@PathVariable Integer id) {
        return ResponseEntity.ok(this.userAddressService.getUserAddress(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<UserAddress>> getAllUserAddress() {
       return ResponseEntity.ok(this.userAddressService.getAllUserAddress());
    }
    @ApiOperation(value = "多条件查询地址")
    @GetMapping("inquire")
    public ResponseEntity<Object> inquire(String type,Integer userid) {
        return ResponseEntity.ok(this.userAddressService.getinquire(type, userid ));
    }
    @ApiOperation(value = "多条件查询")
    @GetMapping("inquires")
    public ResponseEntity< List<UserAddress>> inquire(Integer userid,Integer id) {
        return ResponseEntity.ok(this.userAddressService.updateDefaultAddress(id,userid));
    }


}
