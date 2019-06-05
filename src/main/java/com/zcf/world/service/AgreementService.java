package com.zcf.world.service;

import com.zcf.world.pojo.Agreement;
import com.zcf.world.mapper.AgreementMapper;
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
* @date 2019/06/05
*/
@Service
public class AgreementService{

    @Resource
    private AgreementMapper agreementmapper;

    /**
     * 新增一条数据
     *
     * @param agreement agreement对象
     */
    public void addAgreement(Agreement agreement) {
        int count = this.agreementmapper.insertSelective(agreement);
        if(count != 1){
             throw new CommonException(ExceptionEnum.SAVE_FAILURE);
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     */
    public void deleteAgreementById(Integer id) {

        Example example = new Example(Agreement.class);
        example.createCriteria().andEqualTo("id",id);
        List<Agreement> list = this.agreementmapper.selectByExample(example);
        if (list.size() != 1){
            throw new CommonException(ExceptionEnum.NULL_LIST);
        }
        Agreement agreement = new Agreement();
        agreement.setId(list.get(0).getId());
        updateAgreement(agreement);
    }

    /**
     * 根据主键更新非空数据
     *
     * @param agreement agreement对象
     */
    public void updateAgreement(Agreement agreement) {
        int count = this.agreementmapper.updateByPrimaryKeySelective(agreement);
        if(count != 1){
             throw new CommonException(ExceptionEnum.UPDATE_FAILURE);
        }
    }

    /**
     * 获取所有表中数据
     *
     * @return Agreement对象集合
     */
    public List<Agreement> getAllAgreement() {
        Example example = new Example(Agreement.class);
        List<Agreement> list = this.agreementmapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return list;
    }

    /**
     * 根据主键获取单一数据
     *
     * @param id 主键
     * @return Agreement对象
     */
    public Agreement getAgreement(Integer id){
        Agreement Agreement = this.agreementmapper.selectByPrimaryKey(id);
        if (Agreement == null) {
            throw new CommonException(ExceptionEnum.DATA_DOES_NOT_EXIST);
        }
        return Agreement;
    }

    public Object getType(String type) {
        Example example = new Example(Agreement.class);
        //和等于查询
        example.createCriteria().andEqualTo("type", type);
        List<Agreement> list = this.agreementmapper.selectByExample(example);
        return list;
    }
}
