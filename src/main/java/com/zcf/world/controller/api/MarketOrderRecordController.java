package com.zcf.world.controller.api;


import com.zcf.world.pojo.MarketOrderRecord;
import com.zcf.world.service.MarketOrderRecordService;
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
* @date 2019/06/06
*/
@RestController
@RequestMapping("marketOrderRecord")
@Api(value = "商场订单交易记录控制器", tags = {"商场订单交易记录接口"})
public class MarketOrderRecordController {

    @Autowired
    private MarketOrderRecordService marketOrderRecordService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
                @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "goodsid", value = "商品id", dataType = "Integer"),
                @ApiImplicitParam(name = "totalMoney", value = "总价", dataType = "Date"),
                @ApiImplicitParam(name = "num", value = "数量", dataType = "Integer"),
                @ApiImplicitParam(name = "addtime", value = "添加时间", dataType = "Date"),
    })
    public ResponseEntity<Void> addMarketOrderRecord(MarketOrderRecord marketOrderRecord) {
        this.marketOrderRecordService.addMarketOrderRecord(marketOrderRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteMarketOrderRecordById(@PathVariable Integer id) {
        this.marketOrderRecordService.deleteMarketOrderRecordById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "用户id", dataType = "Integer"),
            @ApiImplicitParam(name = "goodsid", value = "商品id", dataType = "Integer"),
            @ApiImplicitParam(name = "totalMoney", value = "总价", dataType = "Date"),
            @ApiImplicitParam(name = "num", value = "数量", dataType = "Integer"),
            @ApiImplicitParam(name = "addtime", value = "添加时间", dataType = "Date"),
    })
    public ResponseEntity<Void> updateMarketOrderRecord(MarketOrderRecord marketOrderRecord) {
        this.marketOrderRecordService.updateMarketOrderRecord(marketOrderRecord);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<MarketOrderRecord> getMarketOrderRecord(@PathVariable Integer id) {
        return ResponseEntity.ok(this.marketOrderRecordService.getMarketOrderRecord(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<MarketOrderRecord>> getAllMarketOrderRecord() {
       return ResponseEntity.ok(this.marketOrderRecordService.getAllMarketOrderRecord());
    }
}
