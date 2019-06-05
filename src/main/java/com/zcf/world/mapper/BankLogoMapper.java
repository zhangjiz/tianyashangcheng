package com.zcf.world.mapper;

import com.zcf.world.pojo.BankLogo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
/**
* @author xiaodong
* @date 2019/06/05
*/
public interface BankLogoMapper extends Mapper<BankLogo> {
    @Select("select * from bank_logo where bankname=#{bank}")
    BankLogo queryLogoByBank(@Param("bank") String bank);
}
