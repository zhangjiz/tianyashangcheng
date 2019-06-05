package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/06/04
*/
@Data
@Table(name = "banner")
@ApiModel(value = "模型", description = "信息")
public class Banner{
    @Id
    @ApiModelProperty(value = "轮播图ID", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "轮播图", required = true, position = 2)
    private String bannerImg;
    @ApiModelProperty(value = "创建时间", required = true, position = 3)
    private Date creatTime;
    @ApiModelProperty(value = "更新时间", required = true, position = 4)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除:N:未删除；Y:已删除", required = true, position = 5)
    private String Deleted;




}
