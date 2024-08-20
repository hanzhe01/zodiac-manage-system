package com.youlintech.zodiac.chatai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlintech.zodiac.chatai.common.entity.ResponseEntity;
import com.youlintech.zodiac.chatai.domain.MaterialLibrary;
import com.youlintech.zodiac.chatai.model.MaterialLibraryAiPojo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author adc
* @description 针对表【tb_material_library】的数据库操作Service
* @createDate 2024-07-26 12:01:04
*/
@Service
public interface MaterialLibraryService extends IService<MaterialLibrary> {

    /**
     * 自动生成
     * @param constellationName
     * @param number
     * @param remark
     * @return
     */
    ResponseEntity<List<MaterialLibraryAiPojo>> autoAiGeneration(String constellationName, Integer number, String remark);
}
