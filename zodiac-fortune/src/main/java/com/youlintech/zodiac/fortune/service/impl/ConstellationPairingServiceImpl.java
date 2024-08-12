package com.youlintech.zodiac.fortune.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.common.exception.ServiceException;
import com.youlintech.zodiac.common.utils.StringUtils;
import com.youlintech.zodiac.common.utils.bean.BeanValidators;
import com.youlintech.zodiac.fortune.domain.ConstellationPairing;
import com.youlintech.zodiac.fortune.enums.ZodiacEnum;
import com.youlintech.zodiac.fortune.mapper.ConstellationPairingMapper;
import com.youlintech.zodiac.fortune.service.IConstellationPairingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;

/**
 * 星座配对Service业务层处理
 *
 * @author ouyang
 * @date 2024-07-19
 */
@Service
public class ConstellationPairingServiceImpl extends ServiceImpl<ConstellationPairingMapper, ConstellationPairing> implements IConstellationPairingService {

    @Autowired
    private ConstellationPairingMapper pairingMapper;
    @Autowired
    protected Validator validator;


    /**
     * 查询星座配对列表
     *
     * @param constellationPairing 星座配对
     * @return 星座配对
     */
    @Override
    public List<ConstellationPairing> selectConstellationPairingList(ConstellationPairing constellationPairing)
    {
        List<ConstellationPairing> constellationPairingList = pairingMapper.selectList(buildQueryWrapper(constellationPairing));

        return constellationPairingList;
    }

    /**
     * 导入星座配对信息
     *
     * @param constellationPairingList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importPairing(List<ConstellationPairing> constellationPairingList, boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(constellationPairingList) || constellationPairingList.size() == 0)
        {
            throw new ServiceException("导入配对信息数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ConstellationPairing pairing: constellationPairingList)
        {
            try
            {
                // 验证是否存在这个配对数据
                LambdaQueryWrapper<ConstellationPairing> lqw = new LambdaQueryWrapper<>();
                lqw.eq(ConstellationPairing::getBePairedId, pairing.getBePairedId())
                        .eq(ConstellationPairing::getPairingId, pairing.getPairingId());
                ConstellationPairing p = this.getOne(lqw);

                if (StringUtils.isNull(p))
                {
                    BeanValidators.validateWithException(validator, pairing);
                    pairing.setCreateBy(operName);
                    pairingMapper.insert(pairing);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、配对数据导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, pairing);
                    /**
                     * 通过查找星座id和配对星座id的值进行查找数据库是否存在相同的值，获取他的对象属性
                     * isUpdateSupport 如果存在则支持更新已经存在的数据
                     * 通过获取的数据库数据对象的id值设置为文件传递到的对象的id值
                     * 通过id相等更新数据
                     */
                    pairing.setId(p.getId());
                    pairing.setUpdateBy(operName);
                    pairingMapper.updateById(pairing);
                    successNum++;
                }
                successMsg.append("<br/>配对数据" + successNum + ""+ ZodiacEnum.getNameByCode(Math.toIntExact(pairing.getBePairedId()))
                        +"与" + ZodiacEnum.getNameByCode(Math.toIntExact(pairing.getPairingId()))+ " 配对数据更新成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、星座配对 " + ZodiacEnum.getNameByCode(Math.toIntExact(pairing.getPairingId()))
                        +"与" + ZodiacEnum.getNameByCode(Math.toIntExact(pairing.getBePairedId())) + " 配对数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public boolean addOrUpdate(ConstellationPairing constellationPairing) {
        LambdaQueryWrapper<ConstellationPairing> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ConstellationPairing::getBePairedId, constellationPairing.getBePairedId())
                .eq(ConstellationPairing::getPairingId, constellationPairing.getPairingId());
        List<ConstellationPairing> list = this.list(lqw);
        if (list.isEmpty()) {
            return this.save(constellationPairing);
        }
        constellationPairing.setId(list.get(0).getId());
        return this.updateById(constellationPairing);
    }

    private LambdaQueryWrapper<ConstellationPairing> buildQueryWrapper(ConstellationPairing query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<ConstellationPairing> lqw = Wrappers.lambdaQuery();
        lqw.eq(query.getBePairedId() != null, ConstellationPairing::getBePairedId, query.getBePairedId());
        lqw.between(params.get("beginDateUpdated") != null && params.get("endDateUpdated") != null,
                ConstellationPairing::getUpdateTime ,params.get("beginDateUpdated"), params.get("endDateUpdated"));
        return lqw;
    }

}
