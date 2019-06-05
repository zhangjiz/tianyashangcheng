package com.zcf.world.service.layui;

import com.zcf.world.pojo.Announcement;
import com.zcf.world.mapper.AnnouncementMapper;
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
public class LayUiAnnouncementService{

    @Resource
    private AnnouncementMapper LayUiAnnouncementMapper;

    /**
    *新增数据
    */
    public boolean add(Announcement announcement) {
        if (announcement.getCreatTime() == null){
            announcement.setCreatTime(new Date());
        }
        if (announcement.getUpdateTime() == null){
            announcement.setUpdateTime(new Date());
        }
        announcement.setDeleted("N");
        return this.LayUiAnnouncementMapper.insert(announcement) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Announcement.class);
        example.createCriteria().andEqualTo("id",id);
        List<Announcement> list = this.LayUiAnnouncementMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Announcement announcement = new Announcement();
        announcement.setId(list.get(0).getId());
        announcement.setDeleted("Y");
        announcement.setUpdateTime(new Date());
        return this.update(announcement);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Announcement announcement) {
        announcement.setUpdateTime(new Date());
        return this.LayUiAnnouncementMapper.updateByPrimaryKeySelective(announcement) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Announcement> list = this.LayUiAnnouncementMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Announcement.class);
        example.createCriteria().andLike("", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Announcement> list = this.LayUiAnnouncementMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
