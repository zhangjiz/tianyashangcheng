package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/06/05
*/
@Data
@Table(name = "agreement")
@ApiModel(value = "协议表模型", description = "协议表信息")
public class Agreement{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "标题", position = 2)
    private String title;
    @ApiModelProperty(value = "内容", position = 3)
    private String content;
    @ApiModelProperty(value = "顺序", position = 4)
    private Integer level;
    @ApiModelProperty(value = "0用户协议1免责协议", position = 5)
    private String type;
}
