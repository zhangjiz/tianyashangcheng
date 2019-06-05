package com.zcf.world.controller.api;


import com.zcf.world.pojo.UserDeposit;
import com.zcf.world.service.UserDepositService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
* @author xiaodong
* @date 2019/06/05
*/
@RestController
@RequestMapping("userDeposit")
@Api(value = "提现记录控制器", tags = {"提现记录接口"})
public class UserDepositController {

    @Autowired
    private UserDepositService userDepositService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
                @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "depositMoney", value = "提现金额", dataType = "Date"),
                @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
                @ApiImplicitParam(name = "bankName", value = "银行名", dataType = "String"),
            @ApiImplicitParam(name = "bankAccount", value = "银行卡账号", dataType = "String"),
            @ApiImplicitParam(name = "payPwd", value = "支付密码", dataType = "String"),
            @ApiImplicitParam(name = "yanCode", value = "验证码", dataType = "String"),

    })
    public ResponseEntity<Void> addUserDeposit(Integer userid, BigDecimal depositMoney,String userName,
                                               String bankName,String bankAccount,String payPwd,String yanCode) {
        this.userDepositService.addUserDeposit(userid,depositMoney,userName,bankName,bankAccount,payPwd,yanCode);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "获取所有/单个")
    @PostMapping("getAllUserDeposit")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "提现记录id", dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
    })
    public  ResponseEntity<List<UserDeposit>> getAllUserDeposit(Integer id,Integer userid) {
       return ResponseEntity.ok(this.userDepositService.getAllUserDeposit(id,userid));
    }

    @PostMapping("getSms")
    @ApiOperation(value = "获取提现时的验证码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userid", value = "用户id", required = true, dataType = "Integer")
    })
    public ResponseEntity<Void> getForgetCode(Integer userid) {
        this.userDepositService.getSms(userid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

