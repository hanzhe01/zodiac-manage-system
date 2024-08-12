package com.youlintech.zodiac.fortune.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.fortune.domain.Constellation;

import java.util.List;

/**
 * 星座管理Service接口
 *
 * @author oyhz
 * @date 2024-07-17
 */
public interface IConstellationService extends IService<Constellation> {

    /**
     * 查询星座管理列表
     *
     * @param constellation 星座管理
     * @return 星座管理集合
     */
    public List<Constellation> selectConstellationList(Constellation constellation);

    /**
     * 查询星座管理列表
     *
     * @param constellation 星座管理
     * @return 星座管理集合
     */
    public AjaxResult addConstellation(Constellation constellation);

    public AjaxResult updateConstellation(Constellation constellation);

    /**
     * 星座预览接口
     * @param materiallLibraryId 素材id
     * @param pairingId 配对id
     *
     */
    AjaxResult previewDetails(Long materiallLibraryId, Long pairingId);


}
