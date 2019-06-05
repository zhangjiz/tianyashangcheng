package com.zcf.world.service.layui;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcf.world.common.layui.LayUiResult;
import com.zcf.world.mapper.BankLogoMapper;
import com.zcf.world.pojo.BankLogo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Service
public class LayUiBankLogoService{

    @Resource
    private BankLogoMapper LayUiBankLogoMapper;

    /**
    *新增数据
    */
    public boolean add(BankLogo bankLogo) {
        return this.LayUiBankLogoMapper.insert(bankLogo) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(BankLogo.class);
        example.createCriteria().andEqualTo("id",id);
        List<BankLogo> list = this.LayUiBankLogoMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        BankLogo bankLogo = new BankLogo();
        bankLogo.setId(list.get(0).getId());
        return this.update(bankLogo);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(BankLogo bankLogo) {
        return this.LayUiBankLogoMapper.updateByPrimaryKeySelective(bankLogo) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<BankLogo> list = this.LayUiBankLogoMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(BankLogo.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<BankLogo> list = this.LayUiBankLogoMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
