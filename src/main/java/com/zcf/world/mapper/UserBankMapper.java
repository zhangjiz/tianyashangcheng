package com.zcf.world.mapper;

import com.zcf.world.pojo.UserBank;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @author xiaodong
* @date 2019/06/05
*/
public interface UserBankMapper extends Mapper<UserBank> {
    @Select("select * from user_bank where bank_account=#{bankAccount}")
    List<UserBank> queryBankByBankaccount(@Param("bankAccount") String bankAccount);
}
