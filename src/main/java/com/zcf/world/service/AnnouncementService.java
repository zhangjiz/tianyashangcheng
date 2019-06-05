package com.zcf.world.service;

import com.zcf.world.pojo.Announcement;
import com.zcf.world.mapper.AnnouncementMapper;
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
public class AnnouncementService{

    @Resource
    private AnnouncementMapper announcementmapper;

    /**
     * 新增一条数据
     *
     * @param announcement announcement对象
     */
    public void addAnnouncement(Announcement announcement) {
        if (announcement.getCreatTime() == null){
            announcement.setCreatTime(new Date());
        }
        if (announcement.getUpdateTime() == null){
            announcement.setUpdateTime(new Date());
        }
        announcement.setDeleted("N");
        int count = this.announcementmapper.insertSelective(announcement);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteAnnouncementById(Integer id) {

        Example example = new Example(Announcement.class);
        example.createCriteria().andEqualTo("id",id);
        List<Announcement> list = this.announcementmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Announcement announcement = new Announcement();
        announcement.setId(list.get(0).getId());
        announcement.setDeleted("Y");
        announcement.setUpdateTime(new Date());
        updateAnnouncement(announcement);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param announcement announcement对象
     */
    public void updateAnnouncement(Announcement announcement) {
        announcement.setUpdateTime(new Date());
        int count = this.announcementmapper.updateByPrimaryKeySelective(announcement);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Announcement对象集合
     */
    public List<Announcement> getAllAnnouncement() {
        Example example = new Example(Announcement.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<Announcement> list = this.announcementmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Announcement对象
     */
    public Announcement getAnnouncement(Integer id){
        Announcement Announcement = this.announcementmapper.selectByPrimaryKey(id);
        if (Announcement == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Announcement;
    }

}
