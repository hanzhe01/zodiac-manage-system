package com.youlintech.zodiac.fortune.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlintech.zodiac.fortune.domain.Constellation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 星座管理Mapper接口
 *
 * @author oyhz
 * @date 2024-07-17
 */
@Mapper
public interface ConstellationMapper extends BaseMapper<Constellation> {
}
