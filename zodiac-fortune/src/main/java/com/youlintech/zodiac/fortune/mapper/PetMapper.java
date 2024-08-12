package com.youlintech.zodiac.fortune.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlintech.zodiac.fortune.domain.Pet;
import org.apache.ibatis.annotations.Mapper;

/**
 * 白名单Mapper接口
 *
 * @author ouyagn
 * @date 2024-07-25
 */
@Mapper
public interface PetMapper extends BaseMapper<Pet> {
}
