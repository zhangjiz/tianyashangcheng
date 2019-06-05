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
* @date 2019/06/04
*/
@Data
@Table(name = "users")
@ApiModel(value = "模型", description = "信息")
public class Users{
    @Id
    @ApiModelProperty(value = "标识列ID", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户名",required = true, position = 2)
    private String userName;
    @ApiModelProperty(value = "真实姓名",required = true, position = 3)
    private String realName;
    @ApiModelProperty(value = "用户是手机号",required = true,position = 4)
    private  String userPhone;
    @ApiModelProperty(value = "登录密码",required = true,position = 5)
    private String loginPwd;
    @ApiModelProperty(value = "支付密码",required = true, position = 6)
    private String payPwd;
    @ApiModelProperty(value = "推荐码",required = true, position = 7)
    private String recommendedCode;
    @ApiModelProperty(value = "账户余额",required = true, position = 8)
    private BigDecimal money;
    @ApiModelProperty(value = "冻结资金",required = true, position = 9)
    private BigDecimal freezeMoney;
    @ApiModelProperty(value = "提货券",required = true,position = 10)
    private String takeDelivery;
    @ApiModelProperty(value = "是否禁用账户(1禁用2非禁用)",required = true,position = 11)
    private Integer userDisable;
    @ApiModelProperty(value = "用户头像",required = true,position = 12)
    private String userImg;
    @ApiModelProperty(value = "创建时间",required = true,position = 13)
    private Date creatTime;
    @ApiModelProperty(value = "更新时间",required = true,position = 14)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除N未删Y已删",required = true,position = 15)
    private String deleted;
    @ApiModelProperty(value = "备注",required = true,position = 16)
    private String remark;
}
