package com.zcf.world.service.layui;

import com.zcf.world.pojo.UserAddress;
import com.zcf.world.mapper.UserAddressMapper;
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
public class LayUiUserAddressService{

    @Resource
    private UserAddressMapper LayUiUserAddressMapper;

    /**
    *新增数据
    */
    public boolean add(UserAddress userAddress) {
        if (userAddress.getCreatTime() == null){
            userAddress.setCreatTime(new Date());
        }
        if (userAddress.getUpdateTime() == null){
            userAddress.setUpdateTime(new Date());
        }

        return this.LayUiUserAddressMapper.insert(userAddress) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("id",id);
        List<UserAddress> list = this.LayUiUserAddressMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setId(list.get(0).getId());
        userAddress.setUpdateTime(new Date());
        return this.update(userAddress);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(UserAddress userAddress) {
        userAddress.setUpdateTime(new Date());
        return this.LayUiUserAddressMapper.updateByPrimaryKeySelective(userAddress) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<UserAddress> list = this.LayUiUserAddressMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(UserAddress.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<UserAddress> list = this.LayUiUserAddressMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
