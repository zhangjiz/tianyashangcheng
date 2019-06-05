package com.zcf.world.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.common.utils.RandomUtils;
import com.zcf.world.common.utils.SmsUtils;
import com.zcf.world.mapper.UserDepositMapper;
import com.zcf.world.mapper.UsersMapper;
import com.zcf.world.pojo.UserDeposit;
import com.zcf.world.pojo.Users;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaodong
 * @date 2019/06/05
 */
@Service
public class UserDepositService {

    @Resource
    private UserDepositMapper userDepositmapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 新增一条数据
     *
     * @param
     */
    public void addUserDeposit(Integer userid, BigDecimal depositMoney,String userName,
                               String bankName,String bankAccount,String payPwd,String yanCode) {
        Users users = usersMapper.selectByPrimaryKey(userid);
        if (!payPwd.equals(users.getPayPwd())){
            System.out.println("支付密码错误，无法提现");
            throw new CommonException(ExceptionEnum.PAYPWD_IS_NOT_TRUE);
        }
        String yanCode1 = (String) redisTemplate.opsForValue().get("userid" + userid);
        if (!yanCode.equals(yanCode1)){
            System.out.println("验证码错误，无法提现");
            throw new CommonException(ExceptionEnum.REGISTER_CODE_MISMATCH);
        }
        if (users.getMoney().compareTo(depositMoney) == -1) {
            System.out.println("余额不足，无法提现");
            throw new CommonException(ExceptionEnum.PAY_NO_MONEY);
        }


        UserDeposit userDeposit = new UserDeposit();
        userDeposit.setUsersid(userid);
        userDeposit.setDepositMoney(depositMoney);
        userDeposit.setAddtime(new Date());
        userDeposit.setType("0");
        userDeposit.setUserName(userName);
        userDeposit.setBankName(bankName);
        userDeposit.setBankAccount(bankAccount);
        int count = this.userDepositmapper.insertSelective(userDeposit);
        users.setMoney(users.getMoney().subtract(depositMoney));
        if (count != 1) {
            throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }


    /**
     * 获取所有表中数据
     *
     * @return UserDeposit对象集合
     */
    public List<UserDeposit> getAllUserDeposit(Integer id, Integer userid) {
        Example example = new Example(UserDeposit.class);
        Example.Criteria criteria = example.createCriteria();
        if (userid != null) {
            criteria.andEqualTo("userid", userid);
        }
        if (id != null) {
            criteria.andEqualTo("id", id);
        }
        example.setOrderByClause("addtime DESC");
        List<UserDeposit> list = this.userDepositmapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    public void getSms(Integer userid) {
        Users users = usersMapper.selectByPrimaryKey(userid);
        String random = RandomUtils.Random();//获取随机数
        SendSmsResponse response = SmsUtils.sendRegister(users.getUserPhone(), random);//发送短信
        redisTemplate.opsForValue().set("userid" + userid, random, 60 * 3, TimeUnit.SECONDS);
    }
}
