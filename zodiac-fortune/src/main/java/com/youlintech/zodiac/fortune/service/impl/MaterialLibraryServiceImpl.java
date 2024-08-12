package com.youlintech.zodiac.fortune.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.common.exception.ServiceException;
import com.youlintech.zodiac.common.utils.StringUtils;
import com.youlintech.zodiac.common.utils.bean.BeanValidators;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;
import com.youlintech.zodiac.fortune.mapper.MaterialLibraryMapper;
import com.youlintech.zodiac.fortune.service.IMaterialLibraryService;
import com.youlintech.zodiac.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 素材管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Service
public class MaterialLibraryServiceImpl extends ServiceImpl<MaterialLibraryMapper, MaterialLibrary> implements IMaterialLibraryService {

    @Autowired
    private MaterialLibraryMapper materialLibraryMapper;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    protected Validator validator;
    /**
     * 查询素材管理列表
     *
     * @param materialLibrary 素材管理
     * @return 素材管理
     */
    @Override
    public List<MaterialLibrary> selectMaterialLibraryList(MaterialLibrary materialLibrary)
    {
        List<MaterialLibrary> materialLibraryList = materialLibraryMapper.selectList(buildQueryWrapper(materialLibrary));
        getMaterialLibraryListByConstellationId();
        return materialLibraryList;
    }

    /**
     * 导入素材数据
     *
     * @param materialLibraryList 用户数据列表
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importMaterialLibrary(List<MaterialLibrary> materialLibraryList, String operName) {
        if (StringUtils.isNull(materialLibraryList) || materialLibraryList.size() == 0)
        {
            throw new ServiceException("导入素材数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (MaterialLibrary materialLibrary : materialLibraryList)
        {
            try {
                    BeanValidators.validateWithException(validator, materialLibrary);
                    materialLibrary.setCreateBy(operName);
                    materialLibraryMapper.insert(materialLibrary);
                    successNum++;
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、素材,第 " + materialLibraryList.indexOf(materialLibrary)+1 + "行, 导入失败：";
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


    /**
     * 获取每个星座的素材列表
     *
     */
    @Override
    public Map<Long, List<Long>> getMaterialLibraryListByConstellationId() {
        List<MaterialLibrary> materialLibraries = materialLibraryMapper.selectList(null);
        Map<Long, List<Long>> map = materialLibraries.stream()
                .collect(Collectors.groupingBy(
                        MaterialLibrary::getConstellationId,
                        Collectors.mapping(MaterialLibrary::getId, Collectors.toList())
                ));
        System.out.println(map);
        return map;
    }

    private LambdaQueryWrapper<MaterialLibrary> buildQueryWrapper(MaterialLibrary query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<MaterialLibrary> lqw = Wrappers.lambdaQuery();
        lqw.eq(query.getConstellationId() != null, MaterialLibrary::getConstellationId, query.getConstellationId());
        lqw.eq(StringUtils.isNotBlank(query.getLuckyColor()), MaterialLibrary::getLuckyColor, query.getLuckyColor());
        lqw.eq(query.getLuckyNumbers() != null, MaterialLibrary::getLuckyNumbers, query.getLuckyNumbers());
        lqw.eq(query.getIsWeekFortune() != null, MaterialLibrary::getIsWeekFortune, query.getIsWeekFortune());
        lqw.between(params.get("beginGenerationDate") != null && params.get("endGenerationDate") != null,
                MaterialLibrary::getCreateTime ,params.get("beginGenerationDate"), params.get("endGenerationDate"));
        return lqw;
    }

}
