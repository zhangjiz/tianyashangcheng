package com.zcf.world.controller.api;


import com.zcf.world.pojo.BankLogo;
import com.zcf.world.service.BankLogoService;
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
* @author xiaodong
* @date 2019/06/05
*/
@RestController
@RequestMapping("bankLogo")
@Api(value = "银行卡logo控制器", tags = {"银行卡logo接口"})
public class BankLogoController {

    @Autowired
    private BankLogoService bankLogoService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "bankname", value = "银行卡姓名", dataType = "String"),
                @ApiImplicitParam(name = "banklogo", value = "银行卡logo", dataType = "String"),
    })
    public ResponseEntity<Void> addBankLogo(BankLogo bankLogo) {
        this.bankLogoService.addBankLogo(bankLogo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteBankLogoById(@PathVariable Integer id) {
        this.bankLogoService.deleteBankLogoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "bankname", value = "银行卡姓名", dataType = "String"),
            @ApiImplicitParam(name = "banklogo", value = "银行卡logo", dataType = "String"),
    })
    public ResponseEntity<Void> updateBankLogo(BankLogo bankLogo) {
        this.bankLogoService.updateBankLogo(bankLogo);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<BankLogo> getBankLogo(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bankLogoService.getBankLogo(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<BankLogo>> getAllBankLogo() {
       return ResponseEntity.ok(this.bankLogoService.getAllBankLogo());
    }
}
