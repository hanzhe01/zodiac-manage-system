package com.youlintech.zodiac.fortune.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;

import java.util.List;
import java.util.Map;

/**
 * 素材管理Service接口
 *
 * @author ruoyi
 * @date 2024-07-17
 */
public interface IMaterialLibraryService extends IService<MaterialLibrary> {

    /**
     * 查询素材管理列表
     *
     * @param materialLibrary 素材管理
     * @return 素材管理集合
     */
    public List<MaterialLibrary> selectMaterialLibraryList(MaterialLibrary materialLibrary);


    /**
     * 导入素材数据
     *
     * @param materialLibraryList 素材数据列表
     * @param operName 操作用户
     * @return 结果
     */
    public String importMaterialLibrary(List<MaterialLibrary> materialLibraryList, String operName);

    /**
     * 获取每个星座的素材列表
     *
     */
    Map<Long, List<Long>> getMaterialLibraryListByConstellationId();

}
