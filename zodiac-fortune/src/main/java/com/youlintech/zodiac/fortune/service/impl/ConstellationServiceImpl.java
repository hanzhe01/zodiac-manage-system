package com.youlintech.zodiac.fortune.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.redis.RedisCache;
import com.youlintech.zodiac.fortune.constant.RedisConstant;
import com.youlintech.zodiac.fortune.domain.Constellation;
import com.youlintech.zodiac.fortune.domain.ConstellationPairing;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;
import com.youlintech.zodiac.fortune.enums.ZodiacEnum;
import com.youlintech.zodiac.fortune.mapper.ConstellationMapper;
import com.youlintech.zodiac.fortune.model.vo.MaterialLibraryPreviewVO;
import com.youlintech.zodiac.fortune.service.IConstellationPairingService;
import com.youlintech.zodiac.fortune.service.IConstellationService;
import com.youlintech.zodiac.fortune.service.IMaterialLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 星座管理Service业务层处理
 *
 * @author oyhz
 * @date 2024-07-17
 */
@Service
@Slf4j
public class ConstellationServiceImpl extends ServiceImpl<ConstellationMapper, Constellation> implements IConstellationService {

    @Autowired
    private ConstellationMapper constellationMapper;
    @Autowired
    private IMaterialLibraryService materialLibraryService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IConstellationService constellationService;
    @Autowired
    private IConstellationPairingService pairingService;
    /**
     * 查询星座管理列表
     *
     * @param constellation 星座管理
     * @return 星座管理
     */
    @Override
    public List<Constellation> selectConstellationList(Constellation constellation)
    {
        /**
         * 通过Redis获取存储的今日素材id设置
         */
        List<Constellation> constellationList = constellationMapper.selectList(buildQueryWrapper(constellation));
        for (Constellation con : constellationList) {
            String materialIdKey = RedisConstant.getMaterialIdKey(con.getId());
            String updateTimeStr = redisCache.getCacheObject(RedisConstant.AUTO_MATERIAL_LIBRARY_TIME_KEY);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date updateTime = new Date();
            try {
                updateTime = sdf.parse(updateTimeStr);
                log.info("Converted Date:{} ", updateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 获取当前对象的MeterialLibraryId和Redis中对应的值
            Long currentMaterialLibraryId = con.getMeterialLibraryId();
            Long redisMaterialLibraryId = redisCache.getCacheObject(materialIdKey);
            if (currentMaterialLibraryId != null && redisMaterialLibraryId != null
                    && !currentMaterialLibraryId.equals(redisMaterialLibraryId)) {
                con.setMeterialLibraryId(redisMaterialLibraryId);
                materialLibraryService.removeById(currentMaterialLibraryId);
                con.setUpdateTime(updateTime);
                this.updateById(con);
            }
        }
        return constellationList;
    }

    @Override
    public AjaxResult addConstellation(Constellation constellation) {
        AjaxResult ajaxResult = parameterVerify(constellation);
        if (ajaxResult != null) {
            return ajaxResult;
        }
        if (this.save(constellation)) {
            return AjaxResult.success("添加成功", constellation);
        }
        return AjaxResult.error("添加失败");
    }

    @Override
    public AjaxResult updateConstellation(Constellation constellation) {
        AjaxResult ajaxResult = parameterVerify(constellation);
        if (ajaxResult != null) {
            return ajaxResult;
        }
        String materialIdKey = RedisConstant.getMaterialIdKey(constellation.getId());
        redisCache.setCacheObject(materialIdKey, constellation.getMeterialLibraryId());
        if (this.updateById(constellation)) {
            return AjaxResult.success("修改成功", constellation);
        }
        return AjaxResult.error("修改失败");
    }

    @Override
    public AjaxResult previewDetails(Long materiallLibraryId, Long pairingId) {
        MaterialLibrary materialLibrary = materialLibraryService.getById(materiallLibraryId);
        if ( materialLibrary == null) {
            return AjaxResult.error("素材不存在");
        }
        MaterialLibraryPreviewVO vo = BeanUtil.copyProperties(materialLibrary, MaterialLibraryPreviewVO.class);
        Long constellationId = materialLibrary.getConstellationId();
        Constellation constellation = constellationService.getById(constellationId);
        vo.setConstellationName(constellation.getConstellationName());
        vo.setBirthday(constellation.getBirthday());
        if ( pairingId != null) {
            LambdaQueryWrapper<ConstellationPairing> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ConstellationPairing::getPairingId, pairingId).eq(ConstellationPairing::getBePairedId, constellationId);
            ConstellationPairing pairing = pairingService.getOne(wrapper);
            vo.setPairingId(pairing.getPairingId());
            vo.setSuggestion(pairing.getSuggestion());
            vo.setPairingConstellationName(ZodiacEnum.getNameByCode(Math.toIntExact(pairing.getPairingId())));
            vo.setStarLevel(pairing.getStarLevel());
        }
        return AjaxResult.success("查询成功", vo);
    }


    private LambdaQueryWrapper<Constellation> buildQueryWrapper(Constellation query) {
        Map<String, Object> params = query.getParams();
        LambdaQueryWrapper<Constellation> lqw = Wrappers.lambdaQuery();
        lqw.between(params.get("beginUpdataDate") != null && params.get("endUpdataDate") != null,
                Constellation::getUpdateTime ,params.get("beginUpdataDate"), params.get("endUpdataDate"));
        return lqw;
    }

    private AjaxResult parameterVerify(Constellation constellation) {
        Long meterialLibraryId = constellation.getMeterialLibraryId();
        Long weekMeterialLibraryId = constellation.getWeekMeterialLibraryId();

/*        if ( meterialLibraryId == null) {
            return AjaxResult.error("今日素材ID不能为空");
        }
        if ( weekMeterialLibraryId == null) {
            return AjaxResult.error("本周素材ID不能为空");
        }*/

        if ( meterialLibraryId != null) {
            MaterialLibrary materialLibrary = materialLibraryService.getById(meterialLibraryId);
            if ( materialLibrary == null) {
                return AjaxResult.error("今日素材不存在");
            }
            if (!materialLibrary.getConstellationId().toString().equals(constellation.getId().toString())) {
                return AjaxResult.error("今日素材与星座不匹配");
            }
        }
        if ( weekMeterialLibraryId != null ) {
            MaterialLibrary weekMaterialLibrary = materialLibraryService.getById(weekMeterialLibraryId);
            if ( weekMaterialLibrary == null) {
                return AjaxResult.error("本周素材错误不存在");
            }
            if ( weekMaterialLibrary.getIsWeekFortune() != 0 ) {
                return AjaxResult.error("周素材错误,它不属于周素材");
            }
            if (!weekMaterialLibrary.getConstellationId().toString().equals(constellation.getId().toString())) {
                return AjaxResult.error("周素材与星座不匹配");
            }
        }
        return null;
    }

}
