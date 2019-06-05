package com.zcf.world.controller.api;


import com.zcf.world.common.utils.Body;
import com.zcf.world.pojo.Users;
import com.zcf.world.service.UsersService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

/**
* @author 许宝予
* @date 2019/06/04
*/
@RestController
@RequestMapping("users")
@Api(value = "users控制器", tags = {"users接口"})
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
    })
    public ResponseEntity<Void> addUsers(Users users) {
        this.usersService.addUsers(users);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteUsersById(@PathVariable Integer id) {
        this.usersService.deleteUsersById(id);
        //当条件成立时返回前端
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
    })
    public ResponseEntity<Void> updateUsers(Users users) {
        this.usersService.updateUsers(users);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Users> getUsers(@PathVariable Integer id) {
        return ResponseEntity.ok(this.usersService.getUsers(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Users>> getAllUsers() {
       return ResponseEntity.ok(this.usersService.getAllUsers());
    }

    @PostMapping("users")
    @ApiOperation(value = "用户注册", notes = "手机号不能为空")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userPhone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "registerCode", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "loginPwd", value = "登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "recommendedCode", value = "推荐码", required = true, dataType = "String")
    })
    public Body registerUser(
            @RequestParam("realName") String realName,
            @RequestParam("userPhone") String userPhone,
            @RequestParam("registerCode") String registerCode,
            @RequestParam("loginPwd") String loginPwd,
            @RequestParam("recommendedCode") String recommendedCode){
        return this.usersService.registerUser(realName, userPhone, registerCode, loginPwd,recommendedCode);
    }

    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "用户手机号密码登录", notes = "手机号和密码不能为空")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userPhone", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "loginPwd", value = "登录密码", required = true, dataType = "String")
    })
    public ResponseEntity<Object> loginUser(
            @RequestParam String userPhone, @RequestParam String loginPwd
    ) {
        return ResponseEntity.ok(this.usersService.loginUsers(userPhone, loginPwd));
    }

    @PostMapping("forgetCode")
    @ApiOperation(value = "获取忘记密码时的验证码", notes = "手机号必填")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userPhone", value = "用户的手机号", required = true, dataType = "String")
    })
    public ResponseEntity<Void> getForgetCode(String userPhone) {
        this.usersService.getForgetCode(userPhone);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "找回忘记密码", notes = "手机号必填")
    @PostMapping("passWorld")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "realName", value = "用户的姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userPhone", value = "用户的手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "loginPwd", value = "用户的密码", required = true, dataType = "String")
    })
    public ResponseEntity<Void> changePassWorld(@RequestParam("userPhone") String userPhone,
                                                @RequestParam("realName") String realName,
                                                @RequestParam("loginPwd") String loginPwd) {
        this.usersService.updateUserPasswords(realName, userPhone,loginPwd);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @ApiOperation(value = "修改密码(旧新)")
    @PostMapping (value = "updateOldNew", produces = {"application/json;charset=UTF-8"})
    public void updateOldNew(Integer id, String oldpwd, String newpwd) {
        usersService.updateOldNew(id, oldpwd, newpwd);
    }

    @ApiOperation(value = "获取用户的账户余额")
    @PostMapping (value = "/money/{id}", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<BigDecimal> getUsersMoney(@PathVariable Integer id) {
        return ResponseEntity.ok(this.usersService.getUserMoney(id));
    }


}
