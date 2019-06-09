package com.zcf.world.service;

import com.zcf.world.pojo.MarketOrderRecord;
import com.zcf.world.mapper.MarketOrderRecordMapper;
import tk.mybatis.mapper.entity.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import javax.annotation.Resource;
import java.util.List;
/**
* @author 许宝予
* @date 2019/06/06
*/
@Service
public class MarketOrderRecordService{

    @Resource
    private MarketOrderRecordMapper marketOrderRecordmapper;

    /**
     * 新增一条数据
     *
     * @param marketOrderRecord marketOrderRecord对象
     */
    public void addMarketOrderRecord(MarketOrderRecord marketOrderRecord) {

        int count = this.marketOrderRecordmapper.insertSelective(marketOrderRecord);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteMarketOrderRecordById(Integer id) {

        Example example = new Example(MarketOrderRecord.class);
        example.createCriteria().andEqualTo("id",id);
        List<MarketOrderRecord> list = this.marketOrderRecordmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        MarketOrderRecord marketOrderRecord = new MarketOrderRecord();
        marketOrderRecord.setId(list.get(0).getId());
        updateMarketOrderRecord(marketOrderRecord);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param marketOrderRecord marketOrderRecord对象
     */
    public void updateMarketOrderRecord(MarketOrderRecord marketOrderRecord) {
        int count = this.marketOrderRecordmapper.updateByPrimaryKeySelective(marketOrderRecord);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return MarketOrderRecord对象集合
     */
    public List<MarketOrderRecord> getAllMarketOrderRecord() {
        Example example = new Example(MarketOrderRecord.class);

        List<MarketOrderRecord> list = this.marketOrderRecordmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return MarketOrderRecord对象
     */
    public MarketOrderRecord getMarketOrderRecord(Integer id){
        MarketOrderRecord MarketOrderRecord = this.marketOrderRecordmapper.selectByPrimaryKey(id);
        if (MarketOrderRecord == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return MarketOrderRecord;
    }

}
