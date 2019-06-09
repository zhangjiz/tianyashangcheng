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
@Table(name = "order_details")
@ApiModel(value = "模型", description = "信息")
public class OrderDetails{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "产品介绍图片", position = 2)
    private String pic;
    @ApiModelProperty(value = "订货价", position = 3)
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "购买单位（1:一件；2:五件；3:十件）", position = 4)
    private Integer buyer;
    @ApiModelProperty(value = "购买数量", position = 5)
    private String amount;
    @ApiModelProperty(value = "预付款", position = 6)
    private BigDecimal advancePrice;
    @ApiModelProperty(value = "单位差价", position = 7)
    private BigDecimal priceSpread;
    @ApiModelProperty(value = "预定货物手续费", position = 8)
    private BigDecimal bookingCharges;
    @ApiModelProperty(value = "仓库隔夜费", position = 9)
    private BigDecimal overnightStorageCharge;
    @ApiModelProperty(value = "商品详情", position = 10)
    private String commodityDetails;
    @ApiModelProperty(value = "创建时间", position = 11)
    private Date creatTime;
}
