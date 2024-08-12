package com.youlintech.zodiac.chatai.controller;

import com.youlintech.zodiac.chatai.common.entity.ResponseEntity;
import com.youlintech.zodiac.chatai.model.MaterialLibraryAiPojo;
import com.youlintech.zodiac.chatai.service.MaterialLibraryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags="素材自动生成")
@RequestMapping("/materialLibrary")
public class MaterialLibraryController {
    @Autowired
    private MaterialLibraryService materialLibraryService;

    @PostMapping("autoAiGeneration")
    public ResponseEntity<List<MaterialLibraryAiPojo>> autoAiGeneration(@RequestParam(value = "constellationName") String constellationName,
                                   @RequestParam(value = "number") Integer number,
                                   @RequestParam(value = "remark") String remark) {
        ResponseEntity<List<MaterialLibraryAiPojo>> listResponseEntity = materialLibraryService.autoAiGeneration(constellationName, number, remark);
        return listResponseEntity;
    }
}
