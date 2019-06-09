package com.zcf.world.service.layui;

import com.zcf.world.pojo.ProductDisplay;
import com.zcf.world.mapper.ProductDisplayMapper;
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
public class LayUiProductDisplayService{

    @Resource
    private ProductDisplayMapper LayUiProductDisplayMapper;

    /**
    *新增数据
    */
    public boolean add(ProductDisplay productDisplay) {

        return this.LayUiProductDisplayMapper.insert(productDisplay) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(ProductDisplay.class);
        example.createCriteria().andEqualTo("id",id);
        List<ProductDisplay> list = this.LayUiProductDisplayMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        ProductDisplay productDisplay = new ProductDisplay();
        productDisplay.setId(list.get(0).getId());

        return this.update(productDisplay);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(ProductDisplay productDisplay) {

        return this.LayUiProductDisplayMapper.updateByPrimaryKeySelective(productDisplay) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductDisplay> list = this.LayUiProductDisplayMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(ProductDisplay.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<ProductDisplay> list = this.LayUiProductDisplayMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
