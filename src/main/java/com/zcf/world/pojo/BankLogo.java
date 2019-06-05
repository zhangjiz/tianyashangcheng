package com.zcf.world.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Data
@Table(name = "bank_logo")
@ApiModel(value = "银行卡logo模型", description = "银行卡logo信息")
public class BankLogo{
    @Id
    @ApiModelProperty(value = "主键", required = true, position = 1)
    private Integer id;
    @ApiModelProperty(value = "银行卡姓名", position = 2)
    private String bankname;
    @ApiModelProperty(value = "银行卡logo", position = 3)
    private String banklogo;
}
