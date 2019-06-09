package com.zcf.world.service.layui;

import com.zcf.world.pojo.MarketOrderRecord;
import com.zcf.world.mapper.MarketOrderRecordMapper;
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
public class LayUiMarketOrderRecordService{

    @Resource
    private MarketOrderRecordMapper LayUiMarketOrderRecordMapper;

    /**
    *新增数据
    */
    public boolean add(MarketOrderRecord marketOrderRecord) {

        return this.LayUiMarketOrderRecordMapper.insert(marketOrderRecord) == 1;
    }
    /**
    *根据主键删除数据
    */
    public boolean delete(Integer id) {
        Example example = new Example(MarketOrderRecord.class);
        example.createCriteria().andEqualTo("id",id);
        List<MarketOrderRecord> list = this.LayUiMarketOrderRecordMapper.selectByExample(example);
        if (list.size() != 1){
            return false;
        }
        MarketOrderRecord marketOrderRecord = new MarketOrderRecord();
        marketOrderRecord.setId(list.get(0).getId());

        return this.update(marketOrderRecord);
    }

    /**
    *根据主键更新非空数据
    */
    public boolean update(MarketOrderRecord marketOrderRecord) {

        return this.LayUiMarketOrderRecordMapper.updateByPrimaryKeySelective(marketOrderRecord) == 1;
    }

    /**
    *分页查询表中数据
    */
    public LayUiResult query(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<MarketOrderRecord> list = this.LayUiMarketOrderRecordMapper.selectAll();
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }

    /**
    *分页并搜索关键字
    */
     public LayUiResult search(Integer page, Integer limit,String keywords) {
        Example example = new Example(MarketOrderRecord.class);
        example.createCriteria().andLike("id", "%" + keywords + "%");
        PageHelper.startPage(page, limit);
        List<MarketOrderRecord> list = this.LayUiMarketOrderRecordMapper.selectByExample(example);
        return new LayUiResult("0", "查询成功", new PageInfo<>(list).getTotal(), list);
    }
}
