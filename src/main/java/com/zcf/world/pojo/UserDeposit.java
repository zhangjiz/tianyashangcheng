package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Data
@Table(name = "user_deposit")
@ApiModel(value = "提现记录模型", description = "提现记录信息")
public class UserDeposit{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer usersid;
    @ApiModelProperty(value = "提现金额", position = 3)
    private BigDecimal depositMoney;
    @ApiModelProperty(value = "申请时间", position = 4)
    private Date addtime;
    @ApiModelProperty(value = "提现状态（0审核中1已通过2已拒绝）", position = 5)
    private String type;
    @ApiModelProperty(value = "用户名", position = 6)
    private String userName;
    @ApiModelProperty(value = "银行名", position = 7)
    private String bankName;
    @ApiModelProperty(value = "银行卡账号", position = 8)
    private String bankAccount;
}
