package com.youlintech.zodiac.fortune.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlintech.zodiac.fortune.domain.Pet;

import java.util.List;

/**
 * 白名单Service接口
 *
 * @author ouyagn
 * @date 2024-07-25
 */
public interface IPetService extends IService<Pet> {

    /**
     * 查询白名单列表
     *
     * @param pet 白名单
     * @return 白名单集合
     */
    public List<Pet> selectPetList(Pet pet);

    String importUser(List<Pet> petList, String operName);
}
