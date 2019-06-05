package com.zcf.world.service;

import com.zcf.world.common.exception.CommonException;
import com.zcf.world.common.exception.ExceptionEnum;
import com.zcf.world.mapper.BankLogoMapper;
import com.zcf.world.pojo.BankLogo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
/**
* @author xiaodong
* @date 2019/06/05
*/
@Service
public class BankLogoService{

    @Resource
    private BankLogoMapper bankLogomapper;

    /**
     * 新增一条数据
     *
     * @param bankLogo bankLogo对象
     */
    public void addBankLogo(BankLogo bankLogo) {
        int count = this.bankLogomapper.insertSelective(bankLogo);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteBankLogoById(Integer id) {

        Example example = new Example(BankLogo.class);
        example.createCriteria().andEqualTo("id",id);
        List<BankLogo> list = this.bankLogomapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        BankLogo bankLogo = new BankLogo();
        bankLogo.setId(list.get(0).getId());
        updateBankLogo(bankLogo);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param bankLogo bankLogo对象
     */
    public void updateBankLogo(BankLogo bankLogo) {
        int count = this.bankLogomapper.updateByPrimaryKeySelective(bankLogo);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return BankLogo对象集合
     */
    public List<BankLogo> getAllBankLogo() {
        Example example = new Example(BankLogo.class);
        example.createCriteria().andEqualTo("deleted","N");
        List<BankLogo> list = this.bankLogomapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return BankLogo对象
     */
    public BankLogo getBankLogo(Integer id){
        BankLogo BankLogo = this.bankLogomapper.selectByPrimaryKey(id);
        if (BankLogo == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return BankLogo;
    }

}
