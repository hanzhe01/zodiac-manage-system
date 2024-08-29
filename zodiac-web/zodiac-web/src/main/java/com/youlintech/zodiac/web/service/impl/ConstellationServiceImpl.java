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
        // 创建响应实体
        ResponseEntitys<ConstellationDetailsVO> response = new ResponseEntitys<>();
// 尝试获取数据，检查是否为null
        ConstellationDetailsVO constellationFortuneDetail = constellationMapper.getConstellationFortuneDetail(constellationId, pairingId);
        if (constellationFortuneDetail == null) {
            response.setSuccess(false);
            response.setMsg("获取失败,素材未设置");
            response.setCode("601");
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




