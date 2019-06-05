package com.zcf.world.service.layui;

import com.zcf.world.pojo.Banner;
import com.zcf.world.mapper.BannerMapper;
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
public class LayUiBannerService{

    @Resource
    private BannerMapper LayUiBannerMapper;

    /**
    *新增数据
    */
    public boolean add(Banner banner) {
        if (banner.getCreatTime() == null){
            banner.setCreatTime(new Date());
        }
        if (banner.getUpdateTime() == null){
            banner.setUpdateTime(new Date());
        }
        banner.setDeleted("N");
        return this.LayUiBannerMapper.insert(banner) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Banner.class);
        example.createCriteria().andEqualTo("id",id);
        List<Banner> list = this.LayUiBannerMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Banner banner = new Banner();
        banner.setId(list.get(0).getId());
        banner.setDeleted("Y");
        banner.setUpdateTime(new Date());
        return this.update(banner);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Banner banner) {
        banner.setUpdateTime(new Date());
        return this.LayUiBannerMapper.updateByPrimaryKeySelective(banner) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Banner> list = this.LayUiBannerMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Banner.class);
        example.createCriteria().andLike("", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Banner> list = this.LayUiBannerMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
