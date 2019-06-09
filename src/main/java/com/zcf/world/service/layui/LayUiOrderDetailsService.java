package com.zcf.world.service.layui;

import com.zcf.world.pojo.OrderDetails;
import com.zcf.world.mapper.OrderDetailsMapper;
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
* @date 2019/06/06
*/
@Service
public class LayUiOrderDetailsService{

    @Resource
    private OrderDetailsMapper LayUiOrderDetailsMapper;

    /**
    *新增数据
    */
    public boolean add(OrderDetails orderDetails) {

        if (orderDetails.getCreatTime() == null){
            orderDetails.setCreatTime(new Date());
        }
        return this.LayUiOrderDetailsMapper.insert(orderDetails) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(OrderDetails.class);
        example.createCriteria().andEqualTo("id",id);
        List<OrderDetails> list = this.LayUiOrderDetailsMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(list.get(0).getId());
        return this.update(orderDetails);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(OrderDetails orderDetails) {

        return this.LayUiOrderDetailsMapper.updateByPrimaryKeySelective(orderDetails) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<OrderDetails> list = this.LayUiOrderDetailsMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(OrderDetails.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<OrderDetails> list = this.LayUiOrderDetailsMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
