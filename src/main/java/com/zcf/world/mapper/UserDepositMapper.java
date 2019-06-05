package com.zcf.world.mapper;

import com.zcf.world.pojo.UserDeposit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author xiaodong
 * @date 2019/06/05
 */
public interface UserDepositMapper extends Mapper<UserDeposit> {
    @Select("SELECT\n" +
            "user_deposit.id,\n" +
            "user_deposit.usersid,\n" +
            "user_deposit.deposit_money,\n" +
            "user_deposit.addtime,\n" +
            "user_deposit.type,\n" +
            "user_deposit.user_name,\n" +
            "user_deposit.bank_name,\n" +
            "user_deposit.bank_account,\n" +
            "users.user_name\n" +
            "FROM\n" +
            "user_deposit\n" +
            "INNER JOIN users ON user_deposit.usersid = users.id\n" +
            "order by user_deposit.addtime DESC\n" +
            "LIMIT #{page},#{limit}")
    List<UserDeposit> queryALLDeposit(@Param("page") Integer page, @Param("limit") Integer limit);
}
