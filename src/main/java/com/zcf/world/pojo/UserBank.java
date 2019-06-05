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
@Table(name = "user_bank")
@ApiModel(value = "用户绑定银行卡模型", description = "用户绑定银行卡信息")
public class UserBank{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer bankid;
    @ApiModelProperty(value = "用户名", position = 2)
    private String userName;
    @ApiModelProperty(value = "银行卡号", position = 3)
    private String bankAccount;
    @ApiModelProperty(value = "用户联系方式", position = 4)
    private String userPhone;
    @ApiModelProperty(value = "用户身份证", position = 5)
    private String userAccount;
    @ApiModelProperty(value = "开户行", position = 6)
    private String bankName;
    @ApiModelProperty(value = "银行卡logo", position = 7)
    private String bankLogo;
    @ApiModelProperty(value = "添加时间", position = 8)
    private Date creatTime;
}
