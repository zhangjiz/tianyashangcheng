package com.zcf.world.controller.api;


import com.zcf.world.pojo.ProductDisplay;
import com.zcf.world.service.ProductDisplayService;
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
@RequestMapping("productDisplay")
@Api(value = "商品展示控制器", tags = {"商品展示接口"})
public class ProductDisplayController {

    @Autowired
    private ProductDisplayService productDisplayService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer"),
                @ApiImplicitParam(name = "goodsCategory", value = "商品分类", dataType = "String"),
                @ApiImplicitParam(name = "pic", value = "商品图片", dataType = "String"),
                @ApiImplicitParam(name = "tradeName", value = "商品名", dataType = "String"),
                @ApiImplicitParam(name = "price", value = "价格", dataType = "Date"),
                @ApiImplicitParam(name = "couponPrice", value = "提货券价格", dataType = "Date"),
    })
    public ResponseEntity<Void> addProductDisplay(ProductDisplay productDisplay) {
        this.productDisplayService.addProductDisplay(productDisplay);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteProductDisplayById(@PathVariable Integer id) {
        this.productDisplayService.deleteProductDisplayById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer"),
            @ApiImplicitParam(name = "goodsCategory", value = "商品分类", dataType = "String"),
            @ApiImplicitParam(name = "pic", value = "商品图片", dataType = "String"),
            @ApiImplicitParam(name = "tradeName", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "price", value = "价格", dataType = "Date"),
            @ApiImplicitParam(name = "couponPrice", value = "提货券价格", dataType = "Date"),
    })
    public ResponseEntity<Void> updateProductDisplay(ProductDisplay productDisplay) {
        this.productDisplayService.updateProductDisplay(productDisplay);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<ProductDisplay> getProductDisplay(@PathVariable Integer id) {
        return ResponseEntity.ok(this.productDisplayService.getProductDisplay(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<ProductDisplay>> getAllProductDisplay() {
       return ResponseEntity.ok(this.productDisplayService.getAllProductDisplay());
    }
}
