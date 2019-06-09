package com.zcf.world.service;

import com.zcf.world.pojo.ProductDisplay;
import com.zcf.world.mapper.ProductDisplayMapper;
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
public class ProductDisplayService{

    @Resource
    private ProductDisplayMapper productDisplaymapper;

    /**
     * 新增一条数据
     *
     * @param productDisplay productDisplay对象
     */
    public void addProductDisplay(ProductDisplay productDisplay) {

        int count = this.productDisplaymapper.insertSelective(productDisplay);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteProductDisplayById(Integer id) {

        Example example = new Example(ProductDisplay.class);
        example.createCriteria().andEqualTo("id",id);
        List<ProductDisplay> list = this.productDisplaymapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        ProductDisplay productDisplay = new ProductDisplay();
        productDisplay.setId(list.get(0).getId());

        updateProductDisplay(productDisplay);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param productDisplay productDisplay对象
     */
    public void updateProductDisplay(ProductDisplay productDisplay) {

        int count = this.productDisplaymapper.updateByPrimaryKeySelective(productDisplay);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return ProductDisplay对象集合
     */
    public List<ProductDisplay> getAllProductDisplay() {
        Example example = new Example(ProductDisplay.class);
        List<ProductDisplay> list = this.productDisplaymapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return ProductDisplay对象
     */
    public ProductDisplay getProductDisplay(Integer id){
        ProductDisplay ProductDisplay = this.productDisplaymapper.selectByPrimaryKey(id);
        if (ProductDisplay == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return ProductDisplay;
    }

}
