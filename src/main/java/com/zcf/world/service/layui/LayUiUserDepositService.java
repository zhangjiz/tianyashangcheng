package com.zcf.world.service.layui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.mapper.UserDepositMapper;
import com.zcf.world.mapper.UsersMapper;
import com.zcf.world.pojo.UserDeposit;
import com.zcf.world.pojo.Users;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Service
public class LayUiUserDepositService{

    @Resource
    private UserDepositMapper LayUiUserDepositMapper;
    @Resource
    private UsersMapper usersMapper;

    /**
    *新增数据
    */
    public boolean add(UserDeposit userDeposit) {
        return this.LayUiUserDepositMapper.insert(userDeposit) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(UserDeposit.class);
        example.createCriteria().andEqualTo("id",id);
        List<UserDeposit> list = this.LayUiUserDepositMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        UserDeposit userDeposit = new UserDeposit();
        userDeposit.setId(list.get(0).getId());
        return this.update(userDeposit);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(UserDeposit userDeposit) {
        if (userDeposit.getType().equals("2")){
            Users users=usersMapper.selectByPrimaryKey(userDeposit.getUsersid());
            users.setMoney(users.getMoney().add(userDeposit.getDepositMoney()));
            usersMapper.updateByPrimaryKey(users);
        }
        return this.LayUiUserDepositMapper.updateByPrimaryKeySelective(userDeposit) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<UserDeposit> list = this.LayUiUserDepositMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(UserDeposit.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<UserDeposit> list = this.LayUiUserDepositMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
