package com.zcf.world.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.zcf.world.common.utils.Body;
import com.zcf.world.common.utils.RandomUtils;
import com.zcf.world.common.utils.SmsUtils;
import com.zcf.world.pojo.Users;
import com.zcf.world.mapper.UsersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author 许宝予
* @date 2019/06/04
*/
@Service
public class UsersService{
    //设置RedisKey的前缀
    private static final String PHONE_NUMBER="PHONE_NUMBER:";
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private UsersMapper usersmapper;

    /**
     * 新增一条数据
     *
     * @param users users对象
     */
    public void addUsers(Users users) {
        if (users.getCreatTime() == null){
            users.setCreatTime(new Date());
        }
        if (users.getUpdateTime() == null){
            users.setUpdateTime(new Date());
        }
        users.setDeleted("N");
        int count = this.usersmapper.insertSelective(users);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteUsersById(Integer id) {

        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("id",id);
        List<Users> list = this.usersmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Users users = new Users();
        users.setId(list.get(0).getId());
        users.setDeleted("Y");
        users.setUpdateTime(new Date());
        updateUsers(users);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param users users对象
     */
    public void updateUsers(Users users) {
        users.setUpdateTime(new Date());
        int count = this.usersmapper.updateByPrimaryKeySelective(users);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Users对象集合
     */
    public List<Users> getAllUsers() {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Users> list = this.usersmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Users对象
     */
    public Users getUsers(Integer id){
        Users Users = this.usersmapper.selectByPrimaryKey(id);
        if (Users == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Users;
    }
    public Body registerUser(String realName, String userPhone, String registerCode, String loginPwd, String recommendedCode) {
//        判断手机号是否存在
        Users user=this.checkPhone(userPhone);
//        如果信息不为空表示该手机号已被注册过
        if (user != null) {
            return Body.newInstance(400,"手机号已经注册过");
        }
        Users us=new Users();
        us.setRealName(realName);
        us.setUserPhone(userPhone);
        us.setLoginPwd(loginPwd);
        us.setRecommendedCode(recommendedCode);
        us.setCreatTime(new Date());
        us.setUpdateTime(new Date());
        us.setMoney(new BigDecimal(0));



        int insertSelective=this.usersmapper.insertSelective(us);
        if (insertSelective != 1) {
            return  Body.newInstance(400,"注册失败");
        }
        return Body.newInstance(200,"注册成功");
    }
    /**
     * 根据手机号判断用户是否存在
     *
     * @param phone 手机号
     * @return 返回用户的信息
     */
    public Users checkPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            throw new CommonException(ExceptionEnum.PHONE_NUMBER_BE_NULL);
        }
        Example example=new Example(Users.class);
        example.createCriteria().andEqualTo("phone", phone);
        return this.usersmapper.selectOneByExample(example);

    }

    /**
     * 手机号密码登录
     * @param userPhone
     * @param loginPwd
     * @return
     */
    public Object loginUsers(String userPhone, String loginPwd) {
        //查询手机号是否存在
        Users user=this.checkPhone(userPhone);
        //查询用户是否存在
        if (user == null) {
            throw new CommonException(ExceptionEnum.USER_IS_NOT_FOUND);
        }
        if (!StringUtils.equals(user.getLoginPwd(), loginPwd)) {
            throw new CommonException(ExceptionEnum.USER_PASSWORD_MISMATCH);
        }
        return user;
    }

    public void getForgetCode(String userPhone) {
        if (StringUtils.isEmpty(userPhone)) {
            throw new CommonException(ExceptionEnum.PHONE_NUMBER_BE_NULL);
        }
        //查询是否有此用户
        Users user=this.checkPhone(userPhone);
        //没有去注册
        if (user == null) {
            throw new CommonException(ExceptionEnum.USER_IS_NOT_FOUND);
        }
        String random= RandomUtils.Random();//获取随机数

        SendSmsResponse response =  SmsUtils.sendRegister(userPhone,random);//发送短信
        if(response.getCode() == null || !response.getCode().equals("OK")){
            throw new CommonException(ExceptionEnum.REGISTER_CODE_FILE);
        }

        //把验证码放入Redis中并设置3分钟的过期时间
        redisTemplate.opsForValue().set(PHONE_NUMBER+userPhone, random, 60 * 3, TimeUnit.SECONDS);
    }

    /**
     * 找回忘记密码
     * @param userPhone
     * @param realName
     * @param loginPwd
     */
    public void updateUserPasswords(String userPhone, String realName, String loginPwd) {
        Users us=this.checkPhone(userPhone);
        us.setLoginPwd(loginPwd);
        us.setRealName(realName);
        int count=this.usersmapper.updateByPrimaryKeySelective(us);
        if (count != 1) {
            throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }

    }

    /**
     * 修改密码
     * @param id
     * @param oldpwd
     * @param newpwd
     */
    public void updateOldNew(Integer id, String oldpwd, String newpwd) {
        Users user = usersmapper.selectByPrimaryKey(id);
        if (user.getLoginPwd().equals(oldpwd)){
            Users user1 = new Users();
            user1.setId(id);
            user1.setLoginPwd(newpwd);
            int i = usersmapper.updateByPrimaryKeySelective(user1);
            if (i<1){
                throw new CommonException(ExceptionEnum.PARAMETER_CAN_NOT_BE_EMPTY);
            }
        }else {
            throw new CommonException(ExceptionEnum.OLDPWDNO);
        }
    }
    /**
     * 根据用户主键获取剩余金额
     *
     * @param id 主键
     * @return 用户剩余的金额
     */
    public BigDecimal getUserMoney(Integer id) {
        System.out.println(id);
            Users user=this.usersmapper.selectByPrimaryKey(id);
            if (user == null) {
                throw new CommonException(ExceptionEnum.USER_IS_NOT_FOUND);
            }
            return user.getMoney();
        }
    }
