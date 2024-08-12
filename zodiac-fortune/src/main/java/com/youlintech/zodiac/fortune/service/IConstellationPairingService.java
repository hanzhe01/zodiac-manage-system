package com.youlintech.zodiac.fortune.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlintech.zodiac.fortune.domain.ConstellationPairing;

/**
 * 星座配对Service接口
 *
 * @author ouyang
 * @date 2024-07-19
 */
public interface IConstellationPairingService extends IService<ConstellationPairing> {

    /**
     * 查询星座配对列表
     *
     * @param constellationPairing 星座配对
     * @return 星座配对集合
     */
    public List<ConstellationPairing> selectConstellationPairingList(ConstellationPairing constellationPairing);

    String importPairing(List<ConstellationPairing> constellationPairingList, boolean isUpdateSupport, String operName);

    boolean addOrUpdate(ConstellationPairing constellationPairing);

}
