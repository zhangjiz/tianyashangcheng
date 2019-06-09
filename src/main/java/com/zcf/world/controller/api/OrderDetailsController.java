package com.zcf.world.controller.api;


import com.zcf.world.pojo.OrderDetails;
import com.zcf.world.service.OrderDetailsService;
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
@RequestMapping("orderDetails")
@Api(value = "商品订单详情表控制器", tags = {"商品订单详情表接口"})
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Integer"),
                @ApiImplicitParam(name = "pic", value = "产品介绍图片", dataType = "String"),
                @ApiImplicitParam(name = "orderPrice", value = "订货价", dataType = "Date"),
                @ApiImplicitParam(name = "buyer", value = "购买单位（1:一件；2:五件；3:十件）", dataType = "String"),
                @ApiImplicitParam(name = "amount", value = "购买数量", dataType = "String"),
                @ApiImplicitParam(name = "advancePrice", value = "预付款", dataType = "Date"),
                @ApiImplicitParam(name = "priceSpread", value = "单位差价", dataType = "Date"),
                @ApiImplicitParam(name = "bookingCharges", value = "预定货物手续费", dataType = "Date"),
                @ApiImplicitParam(name = "overnightStorageCharge", value = "仓库隔夜费", dataType = "Date"),
                @ApiImplicitParam(name = "commodityDetails", value = "商品详情", dataType = "String"),
                @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
    })
    public ResponseEntity<Void> addOrderDetails(OrderDetails orderDetails) {
        this.orderDetailsService.addOrderDetails(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteOrderDetailsById(@PathVariable Integer id) {
        this.orderDetailsService.deleteOrderDetailsById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Integer"),
            @ApiImplicitParam(name = "pic", value = "产品介绍图片", dataType = "String"),
            @ApiImplicitParam(name = "orderPrice", value = "订货价", dataType = "Date"),
            @ApiImplicitParam(name = "buyer", value = "购买单位（1:一件；2:五件；3:十件）", dataType = "Integer"),
            @ApiImplicitParam(name = "amount", value = "购买数量", dataType = "String"),
            @ApiImplicitParam(name = "advancePrice", value = "预付款", dataType = "Date"),
            @ApiImplicitParam(name = "priceSpread", value = "单位差价", dataType = "Date"),
            @ApiImplicitParam(name = "bookingCharges", value = "预定货物手续费", dataType = "Date"),
            @ApiImplicitParam(name = "overnightStorageCharge", value = "仓库隔夜费", dataType = "Date"),
            @ApiImplicitParam(name = "commodityDetails", value = "商品详情", dataType = "String"),
            @ApiImplicitParam(name = "creatTime", value = "创建时间", dataType = "Date"),
    })
    public ResponseEntity<Void> updateOrderDetails(OrderDetails orderDetails) {
        this.orderDetailsService.updateOrderDetails(orderDetails);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(this.orderDetailsService.getOrderDetails(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
       return ResponseEntity.ok(this.orderDetailsService.getAllOrderDetails());
    }
}
