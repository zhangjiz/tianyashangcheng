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
* @date 2019/06/06
*/
@Data
@Table(name = "product_display")
@ApiModel(value = "模型", description = "信息")
public class ProductDisplay{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "商品分类", position = 2)
    private String goodsCategory;
    @ApiModelProperty(value = "商品图片", position = 3)
    private String pic;
    @ApiModelProperty(value = "商品名", position = 4)
    private String tradeName;
    @ApiModelProperty(value = "价格", position = 5)
    private BigDecimal price;
    @ApiModelProperty(value = "提货券价格", position = 6)
    private BigDecimal couponPrice;
}
