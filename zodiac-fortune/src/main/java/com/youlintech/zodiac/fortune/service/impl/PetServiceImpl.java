package com.youlintech.zodiac.fortune.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.common.exception.ServiceException;
import com.youlintech.zodiac.common.utils.StringUtils;
import com.youlintech.zodiac.common.utils.bean.BeanValidators;
import com.youlintech.zodiac.fortune.domain.Pet;
import com.youlintech.zodiac.fortune.mapper.PetMapper;
import com.youlintech.zodiac.fortune.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;

/**
 * 白名单Service业务层处理
 *
 * @author ouyagn
 * @date 2024-07-25
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements IPetService {

    @Autowired
    private PetMapper petMapper;
    @Autowired
    protected Validator validator;

    /**
     * 查询白名单列表
     *
     * @param pet 白名单
     * @return 白名单
     */
    @Override
    public List<Pet> selectPetList(Pet pet) {
        List<Pet> petList = petMapper.selectList(buildQueryWrapper(pet));
        return petList;
    }

    @Override
    public String importUser(List<Pet> petList, String operName) {
        if (StringUtils.isNull(petList) || petList.size() == 0) {
            throw new ServiceException("导入设备白名单数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Pet pet : petList) {
            try {
                BeanValidators.validateWithException(validator, pet);
                pet.setCreateBy(operName);
                this.save(pet);
                successNum++;
                successMsg.append("<br/>" + successNum + "、设备白名单数据 " + pet.getPetId() + " 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、设备白名单数据 " + pet.getPetId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    private LambdaQueryWrapper<Pet> buildQueryWrapper(Pet query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<Pet> lqw = Wrappers.lambdaQuery();
        lqw.eq(query.getPetId()!=null, Pet::getPetId, query.getPetId());
        return lqw;
    }

}
