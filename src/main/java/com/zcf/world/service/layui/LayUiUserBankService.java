package com.zcf.world.service.layui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.mapper.UserBankMapper;
import com.zcf.world.pojo.UserBank;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Service
public class LayUiUserBankService{

    @Resource
    private UserBankMapper LayUiUserBankMapper;

    /**
    *新增数据
    */
    public boolean add(UserBank userBank) {
        if (userBank.getCreatTime() == null){
            userBank.setCreatTime(new Date());
        }
//        if (userBank.getUpdateTime() == null){
//            userBank.setUpdateTime(new Date());
//        }
//        userBank.setDeleted("N");
        return this.LayUiUserBankMapper.insert(userBank) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(UserBank.class);
        example.createCriteria().andEqualTo("id",id);
        List<UserBank> list = this.LayUiUserBankMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        UserBank userBank = new UserBank();
//        userBank.setId(list.get(0).getId());
//        userBank.setDeleted("Y");
//        userBank.setUpdateTime(new Date());
        return this.update(userBank);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(UserBank userBank) {
//        userBank.setUpdateTime(new Date());
        return this.LayUiUserBankMapper.updateByPrimaryKeySelective(userBank) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<UserBank> list = this.LayUiUserBankMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(UserBank.class);
        example.createCriteria().andLike("bankid", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<UserBank> list = this.LayUiUserBankMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
