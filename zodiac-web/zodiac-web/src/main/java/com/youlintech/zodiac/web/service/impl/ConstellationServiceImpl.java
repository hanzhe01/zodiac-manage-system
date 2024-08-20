package com.youlintech.zodiac.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlintech.zodiac.web.domain.Constellation;
import com.youlintech.zodiac.web.entity.ResponseEntitys;
import com.youlintech.zodiac.web.mapper.ConstellationMapper;
import com.youlintech.zodiac.web.model.vo.ConstellationDetailsVO;
import com.youlintech.zodiac.web.service.ConstellationService;
import com.youlintech.zodiac.web.service.MaterialLibraryService;
import com.youlintech.zodiac.web.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 欧阳涵哲
* @description 针对表【tb_constellation】的数据库操作Service实现
* @createDate 2024-07-22 22:22:35
*/
@Service
public class ConstellationServiceImpl extends ServiceImpl<ConstellationMapper, Constellation>
    implements ConstellationService{
    @Autowired
    private ConstellationMapper constellationMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MaterialLibraryService materialLibraryService;

    @Override
    public ResponseEntitys<ConstellationDetailsVO> getFortuneDetails(Long constellationId, Long pairingId) {
        // 检查传入参数是否为null
        if (constellationId == null || pairingId == null) {
            throw new IllegalArgumentException("constellationId and pairingId cannot be null");
        }
// 尝试获取数据，检查是否为null
        ConstellationDetailsVO constellationFortuneDetail = constellationMapper.getConstellationFortuneDetail(constellationId, pairingId);
        if (constellationFortuneDetail == null) {
            // 可以抛出自定义异常或者返回错误响应
        }

        String materialIdKey = "constellation:" + constellationId + ":material_id";

// 获取缓存中的素材ID，检查是否为null
        Object cacheObject = redisCache.getCacheObject(materialIdKey);
        Long cachedMeterialLibraryId = cacheObject != null ? (Long) cacheObject : null;
        String redisMeterialLibraryId = cachedMeterialLibraryId != null ? cachedMeterialLibraryId.toString() : null;

// 创建响应实体
        ResponseEntitys<ConstellationDetailsVO> response = new ResponseEntitys<>();

// 比较素材ID
        if (constellationFortuneDetail.getMeterialLibraryId() != null && constellationFortuneDetail.getMeterialLibraryId().toString().equals(redisMeterialLibraryId)) {
            response.setData(constellationFortuneDetail);
            response.setSuccess(true);
            response.setMsg("查询成功");
            response.setCode("200");
            return response;
        }

// 如果不一致，从数据库获取Constellation对象
        Constellation constellation = this.getById(constellationFortuneDetail.getId());
        if (constellation != null) {
            materialLibraryService.removeById(constellation.getMeterialLibraryId());
            constellation.setMeterialLibraryId(cachedMeterialLibraryId);
            this.updateById(constellation);
        } else {
            response.setSuccess(false);
            return response;
        }

// 再次获取数据
        ConstellationDetailsVO constellationDetailsVO = constellationMapper.getConstellationFortuneDetail(constellationId, pairingId);
        response.setData(constellationDetailsVO);
        response.setSuccess(true);
        response.setMsg("查询成功");
        response.setCode("200");
        return response;

    }
}




