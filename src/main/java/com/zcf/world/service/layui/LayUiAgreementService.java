package com.zcf.world.service.layui;

import com.zcf.world.pojo.Agreement;
import com.zcf.world.mapper.AgreementMapper;
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
public class LayUiAgreementService{

    @Resource
    private AgreementMapper LayUiAgreementMapper;

    /**
    *新增数据
    */
    public boolean add(Agreement agreement) {
        return this.LayUiAgreementMapper.insert(agreement) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(Agreement.class);
        example.createCriteria().andEqualTo("id",id);
        List<Agreement> list = this.LayUiAgreementMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        Agreement agreement = new Agreement();
        agreement.setId(list.get(0).getId());
        return this.update(agreement);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(Agreement agreement) {
        return this.LayUiAgreementMapper.updateByPrimaryKeySelective(agreement) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Agreement> list = this.LayUiAgreementMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(Agreement.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<Agreement> list = this.LayUiAgreementMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
