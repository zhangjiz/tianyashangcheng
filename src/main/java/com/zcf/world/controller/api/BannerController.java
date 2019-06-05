package com.zcf.world.controller.api;


import com.zcf.world.pojo.Banner;
import com.zcf.world.service.BannerService;
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
@RequestMapping("banner")
@Api(value = "banner控制器", tags = {"banner接口"})
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
    })
    public ResponseEntity<Void> addBanner(Banner banner) {
        this.bannerService.addBanner(banner);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteBannerById(@PathVariable Integer id) {
        this.bannerService.deleteBannerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
    })
    public ResponseEntity<Void> updateBanner(Banner banner) {
        this.bannerService.updateBanner(banner);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Banner> getBanner(@PathVariable Integer id) {
        return ResponseEntity.ok(this.bannerService.getBanner(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Banner>> getAllBanner() {
       return ResponseEntity.ok(this.bannerService.getAllBanner());
    }
}
