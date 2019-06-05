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
@Table(name = "user_address")
@ApiModel(value = "用户收货地址模型", description = "用户收货地址信息")
public class UserAddress{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userid;
    @ApiModelProperty(value = "收货人姓名", position = 3)
    private String userName;
    @ApiModelProperty(value = "收货人联系方式", position = 4)
    private String userPhone;
    @ApiModelProperty(value = "所在地区", position = 5)
    private String area;
    @ApiModelProperty(value = "详细地址", position = 6)
    private String detailAddress;
    @ApiModelProperty(value = "添加时间", position = 7)
    private Date updateTime;
    @ApiModelProperty(value = "添加时间", position = 7)
    private Date creatTime;
    @ApiModelProperty(value = "是否默认（0默认1普通）", position = 8)
    private String type;
}
