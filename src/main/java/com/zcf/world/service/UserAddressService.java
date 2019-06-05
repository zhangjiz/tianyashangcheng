package com.zcf.world.service;

import com.zcf.world.pojo.UserAddress;
import com.zcf.world.mapper.UserAddressMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
* @author 许宝予
* @date 2019/06/04
*/
@Service
public class UserAddressService{

    @Resource
    private UserAddressMapper userAddressmapper;

    /**
     * 新增一条数据
     *
     * @param userAddress userAddress对象
     */
    public void addUserAddress(UserAddress userAddress) {
        if (userAddress.getCreatTime() == null){
            userAddress.setCreatTime(new Date());
        }
        if (userAddress.getUpdateTime() == null){
            userAddress.setUpdateTime(new Date());
        }
        userAddress.setType("1");
        int count = this.userAddressmapper.insertSelective(userAddress);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }

    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteUserAddressById(Integer id) {

        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("id",id);
        List<UserAddress> list = this.userAddressmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        UserAddress userAddress = new UserAddress();
        userAddress.setId(list.get(0).getId());
        userAddress.setUpdateTime(new Date());
        updateUserAddress(userAddress);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param userAddress userAddress对象
     */
    public void updateUserAddress(UserAddress userAddress) {

        userAddress.setUpdateTime(new Date());
        int count = this.userAddressmapper.updateByPrimaryKeySelective(userAddress);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return UserAddress对象集合
     */
    public List<UserAddress> getAllUserAddress() {
        Example example = new Example(UserAddress.class);
        List<UserAddress> list = this.userAddressmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return UserAddress对象
     */
    public UserAddress getUserAddress(Integer id){
        UserAddress UserAddress = this.userAddressmapper.selectByPrimaryKey(id);
        if (UserAddress == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return UserAddress;
    }

    /**
     * 条件查询地址
     * @param type
     * @param userid
     * @return
     */
    public Object getinquire(String type, Integer userid) {
       Example example = new Example(UserAddress.class);
        //和等于查询
        example.createCriteria().andEqualTo("userid", userid)
                .andEqualTo("type", type);
        List<UserAddress> list = this.userAddressmapper.selectByExample(example);
        return list;
    }

    public  List<UserAddress> updateDefaultAddress(Integer userid,Integer id){
        Example example = new Example(UserAddress.class);
        //和等于查询
        example.createCriteria().andEqualTo("userid", userid);
        List<UserAddress> list = this.userAddressmapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            for(UserAddress address : list){
                if(address.getId() == id){
                    address.setType("0");
                }else {
                    address.setType("1");
                }
                this.userAddressmapper.updateByPrimaryKeySelective(address);
            }
        }
        return list;
    }

}
