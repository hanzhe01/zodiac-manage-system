package com.youlintech.zodiac.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlintech.zodiac.web.domain.Constellation;
import com.youlintech.zodiac.web.model.vo.ConstellationDetailsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 欧阳涵哲
* @description 针对表【tb_constellation】的数据库操作Mapper
* @createDate 2024-07-22 22:22:35
* @Entity com.youlintech.zodiac.web.domain.Constellation
*/
@Mapper
public interface ConstellationMapper extends BaseMapper<Constellation> {
    public ConstellationDetailsVO getConstellationFortuneDetail(
            @Param("constellationId") Long constellationId,
            @Param("pairingId") Long pairingId);
}




