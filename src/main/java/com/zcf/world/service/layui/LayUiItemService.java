package com.zcf.world.service.layui;

import com.zcf.world.pojo.Item;
import com.zcf.world.mapper.ItemMapper;
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
* @date 2019/06/05
*/
@Service
public class LayUiItemService{

    @Resource
    private ItemMapper LayUiItemMapper;

    /**
    *新增数据
    */
    public boolean add(Item item) {
        if (item.getCreatTime() == null){
            item.setCreatTime(new Date());
        }
        if (item.getUpdateTime() == null){
            item.setUpdateTime(new Date());
        }
        item.setDeleted("N");
        return this.LayUiItemMapper.insert(item) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Item.class);
        example.createCriteria().andEqualTo("id",id);
        List<Item> list = this.LayUiItemMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Item item = new Item();
        item.setId(list.get(0).getId());
        item.setDeleted("Y");
        item.setUpdateTime(new Date());
        return this.update(item);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Item item) {
        item.setUpdateTime(new Date());
        return this.LayUiItemMapper.updateByPrimaryKeySelective(item) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Item> list = this.LayUiItemMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Item.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Item> list = this.LayUiItemMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
