package com.youlintech.zodiac.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youlintech.zodiac.web.domain.Pet;
import com.youlintech.zodiac.web.entity.ResponseEntitys;
import com.youlintech.zodiac.web.model.vo.ConstellationDetailsVO;
import com.youlintech.zodiac.web.service.ConstellationService;
import com.youlintech.zodiac.web.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author oyhz
 */
@RestController
@Api(tags = "星座运势")
@RequestMapping("/fortune/{constellationId}")
public class FortuneController {

    @Autowired
    private ConstellationService constellationService;
    @Autowired
    private PetService petService;

    /**
     * 通过GET请求获取星座运势详情。
     *
     * 此方法处理来自客户端的GET请求，旨在获取指定星座及其配对星座的详细运势信息。
     * 请求的URL模式包含两个路径变量：constellationId和pairingConstellationId，分别代表星座ID和配对星座ID。
     * 方法调用星座服务层，获取并返回星座详情的响应实体。
     *
     * @param constellationId 请求路径变量，代表星座ID，用于查询特定星座的运势详情。
     * @param pairingId 请求路径变量，代表配对星座ID，用于查询配对星座的运势详情。
     * @return ResponseEntity<ConstellationDetailsVO> 返回包含星座详情的响应实体，响应实体中可能包含HTTP状态码、头信息和主体。
     */
    @GetMapping("/pairing/{pairingId}")
    @ApiOperation(value = "获取星座及其配对星座的运势详情", notes = "根据提供的星座ID和配对星座ID，返回星座的详细运势信息")
    public ResponseEntitys<ConstellationDetailsVO> getConstellationFortuneDetail(
            @ApiParam(value = "星座ID", required = true) @PathVariable("constellationId") Long constellationId,
            @ApiParam(value = "配对星座ID", required = true) @PathVariable("pairingId") Long pairingId,
            @RequestParam(required = false) String petId) {
        if (petId == null || petId.isEmpty()) {
            ResponseEntitys<ConstellationDetailsVO> response = new ResponseEntitys<>();
            response.setSuccess(false);
            response.setData(null);
            response.setCode("401");
            response.setMsg("Missing petid parameter");
            return response;
        }
        LambdaQueryWrapper<Pet> petWrapper = new LambdaQueryWrapper<>();
        petWrapper.eq(Pet::getPetId, petId);
        Pet pet = petService.getOne(petWrapper);
        if (pet == null) {
            ResponseEntitys<ConstellationDetailsVO> response = new ResponseEntitys<>();
            response.setSuccess(false);
            response.setData(null);
            response.setCode("401");
            response.setMsg("Pet not found");
            return response;
        }
        return constellationService.getFortuneDetails(constellationId, pairingId);
    }

}
