package com.zcf.world.controller.api;


import com.zcf.world.pojo.Announcement;
import com.zcf.world.service.AnnouncementService;
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
@RequestMapping("announcement")
@Api(value = "announcement控制器", tags = {"announcement接口"})
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @ApiOperation(value = "新增")
    @PostMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
    })
    public ResponseEntity<Void> addAnnouncement(Announcement announcement) {
        this.announcementService.addAnnouncement(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value="/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Void> deleteAnnouncementById(@PathVariable Integer id) {
        this.announcementService.deleteAnnouncementById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "修改")
    @PutMapping(produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams(value = {
    })
    public ResponseEntity<Void> updateAnnouncement(Announcement announcement) {
        this.announcementService.updateAnnouncement(announcement);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "获取单个")
    @GetMapping(value = "/{id}",produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "id", value = "主键", dataType = "Integer")
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable Integer id) {
        return ResponseEntity.ok(this.announcementService.getAnnouncement(id));
    }

    @ApiOperation(value = "获取所有")
    @GetMapping(produces = {"application/json;charset=UTF-8"})
    public  ResponseEntity<List<Announcement>> getAllAnnouncement() {
       return ResponseEntity.ok(this.announcementService.getAllAnnouncement());
    }
}
