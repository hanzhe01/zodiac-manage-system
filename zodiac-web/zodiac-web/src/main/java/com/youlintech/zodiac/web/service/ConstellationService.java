package com.youlintech.zodiac.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlintech.zodiac.web.domain.Constellation;
import com.youlintech.zodiac.web.entity.ResponseEntitys;
import com.youlintech.zodiac.web.model.vo.ConstellationDetailsVO;
import org.springframework.stereotype.Service;

/**
* @author 欧阳涵哲
* @description 针对表【tb_constellation】的数据库操作Service
* @createDate 2024-07-22 22:22:35
*/
@Service
public interface ConstellationService extends IService<Constellation> {
    /**
     *  获取星座运势详情
     * @param constellationId
     * @param pairingId
     * @return
     */
    ResponseEntitys<ConstellationDetailsVO> getFortuneDetails(Long constellationId, Long pairingId);
}
