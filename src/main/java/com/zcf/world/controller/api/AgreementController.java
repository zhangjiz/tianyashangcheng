package com.zcf.world.controller.api;


import com.zcf.world.pojo.Agreement;
import com.zcf.world.service.AgreementService;
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
* @date 2019/06/05
*/
@RestController
@RequestMapping("agreement")
@Api(value = "协议表控制器", tags = {"协议表接口"})
public class AgreementController {

    @Autowired
    private AgreementService agreementService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "title", value = "标题", dataType = "String"),
                @ApiImplicitParam(name = "content", value = "内容", dataType = "String"),
                @ApiImplicitParam(name = "level", value = "顺序", dataType = "Integer"),
                @ApiImplicitParam(name = "type", value = "0用户协议1免责协议", dataType = "String"),
    })
    public ResponseEntity<Void> addAgreement(Agreement agreement) {
        this.agreementService.addAgreement(agreement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteAgreementById(@PathVariable Integer id) {
        this.agreementService.deleteAgreementById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "标题", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", dataType = "String"),
            @ApiImplicitParam(name = "level", value = "顺序", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "0用户协议1免责协议", dataType = "String"),
    })
    public ResponseEntity<Void> updateAgreement(Agreement agreement) {
        this.agreementService.updateAgreement(agreement);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Agreement> getAgreement(@PathVariable Integer id) {
        return ResponseEntity.ok(this.agreementService.getAgreement(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Agreement>> getAllAgreement() {
       return ResponseEntity.ok(this.agreementService.getAllAgreement());
    }
    @ApiOperation(value = "协议类型")
    @GetMapping(value = "type",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "type", value = "0用户协议1免责协议", dataType = "String")
    public ResponseEntity<Object> getType(String type) {
        return ResponseEntity.ok(this.agreementService.getType(type));
    }
}
