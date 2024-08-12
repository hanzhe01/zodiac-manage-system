package com.youlintech.zodiac.fortune.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 素材管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Mapper
public interface MaterialLibraryMapper extends BaseMapper<MaterialLibrary> {
    List<MaterialLibrary> selectConstellationIdAndId();
}
