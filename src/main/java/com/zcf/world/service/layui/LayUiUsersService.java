package com.zcf.world.service.layui;

import com.zcf.world.pojo.Users;
import com.zcf.world.mapper.UsersMapper;
import com.zcf.world.common.layui.LayUiResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/06/04
*/
@Service
public class LayUiUsersService{

    @Resource
    private UsersMapper LayUiUsersMapper;

    /**
    *新增数据
    */
    public boolean add(Users users) {
        if (users.getCreatTime() == null){
            users.setCreatTime(new Date());
        }
        if (users.getUpdateTime() == null){
            users.setUpdateTime(new Date());
        }
        users.setDeleted("N");
        return this.LayUiUsersMapper.insert(users) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Users.class);
        example.createCriteria().andEqualTo("id",id);
        List<Users> list = this.LayUiUsersMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Users users = new Users();
        users.setId(list.get(0).getId());
        users.setDeleted("Y");
        users.setUpdateTime(new Date());
        return this.update(users);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Users users) {
        users.setUpdateTime(new Date());
        return this.LayUiUsersMapper.updateByPrimaryKeySelective(users) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Users> list = this.LayUiUsersMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Users.class);
        example.createCriteria().andLike("", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Users> list = this.LayUiUsersMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
