package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author 许宝予
* @date 2019/06/06
*/
@Data
@Table(name = "market_order_record")
@ApiModel(value = "商场订单交易记录模型", description = "商场订单交易记录信息")
public class MarketOrderRecord{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userid;
    @ApiModelProperty(value = "商品id", position = 3)
    private Integer goodsid;
    @ApiModelProperty(value = "总价", position = 4)
    private Date totalMoney;
    @ApiModelProperty(value = "数量", position = 5)
    private Integer num;
    @ApiModelProperty(value = "添加时间", position = 6)
    private Date addtime;
}
