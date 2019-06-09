package com.zcf.world.service;

import com.zcf.world.pojo.OrderDetails;
import com.zcf.world.mapper.OrderDetailsMapper;
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
* @date 2019/06/06
*/
@Service
public class OrderDetailsService{

    @Resource
    private OrderDetailsMapper orderDetailsmapper;

    /**
     * 新增一条数据
     *
     * @param orderDetails orderDetails对象
     */
    public void addOrderDetails(OrderDetails orderDetails) {
        if (orderDetails.getCreatTime() == null){
            orderDetails.setCreatTime(new Date());
        }

        int count = this.orderDetailsmapper.insertSelective(orderDetails);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteOrderDetailsById(Integer id) {

        Example example = new Example(OrderDetails.class);
        example.createCriteria().andEqualTo("id",id);
        List<OrderDetails> list = this.orderDetailsmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(list.get(0).getId());

        updateOrderDetails(orderDetails);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param orderDetails orderDetails对象
     */
    public void updateOrderDetails(OrderDetails orderDetails) {

        int count = this.orderDetailsmapper.updateByPrimaryKeySelective(orderDetails);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return OrderDetails对象集合
     */
    public List<OrderDetails> getAllOrderDetails() {
        Example example = new Example(OrderDetails.class);
        List<OrderDetails> list = this.orderDetailsmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return OrderDetails对象
     */
    public OrderDetails getOrderDetails(Integer id){
        OrderDetails OrderDetails = this.orderDetailsmapper.selectByPrimaryKey(id);
        if (OrderDetails == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return OrderDetails;
    }

}
