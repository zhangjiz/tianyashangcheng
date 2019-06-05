package com.zcf.world.service;

import com.zcf.world.pojo.Banner;
import com.zcf.world.mapper.BannerMapper;
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
public class BannerService{

    @Resource
    private BannerMapper bannermapper;

    /**
     * 新增一条数据
     *
     * @param banner banner对象
     */
    public void addBanner(Banner banner) {
        if (banner.getCreatTime() == null){
            banner.setCreatTime(new Date());
        }
        if (banner.getUpdateTime() == null){
            banner.setUpdateTime(new Date());
        }
        banner.setDeleted("N");
        int count = this.bannermapper.insertSelective(banner);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteBannerById(Integer id) {

        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo("id",id);
        List<Banner> list = this.bannermapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Banner banner = new Banner();
        banner.setId(list.get(0).getId());
        banner.setDeleted("Y");
        banner.setUpdateTime(new Date());
        updateBanner(banner);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param banner banner对象
     */
    public void updateBanner(Banner banner) {
        banner.setUpdateTime(new Date());
        int count = this.bannermapper.updateByPrimaryKeySelective(banner);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Banner对象集合
     */
    public List<Banner> getAllBanner() {
        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Banner> list = this.bannermapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Banner对象
     */
    public Banner getBanner(Integer id){
        Banner Banner = this.bannermapper.selectByPrimaryKey(id);
        if (Banner == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Banner;
    }

}
