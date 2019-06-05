package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/06/05
*/
@Data
@Table(name = "item")
@ApiModel(value = "商品表模型", description = "商品表信息")
public class Item{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "商品标题", position = 2)
    private String itemTitle;
    @ApiModelProperty(value = "商品详情", position = 3)
    private String itemDetails;
    @ApiModelProperty(value = "商品进价", position = 4)
    private BigDecimal itemPurchase;
    @ApiModelProperty(value = "售价", position = 5)
    private BigDecimal itemPrice;
    @ApiModelProperty(value = "提货券价格", position = 6)
    private BigDecimal takeDeliveryPrice;
    @ApiModelProperty(value = "商品列表图片", position = 7)
    private String itemImg;
    @ApiModelProperty(value = "", position = 8)
    private Date creatTime;
    @ApiModelProperty(value = "", position = 9)
    private Date updateTime;
    @ApiModelProperty(value = "", position = 10)
    private String deleted;
    @ApiModelProperty(value = "", position = 11)
    private String remark;
}
