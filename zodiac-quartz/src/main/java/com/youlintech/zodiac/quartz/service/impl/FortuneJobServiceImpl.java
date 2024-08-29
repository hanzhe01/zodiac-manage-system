package com.youlintech.zodiac.quartz.service.impl;


import com.youlintech.zodiac.common.core.redis.RedisCache;
import com.youlintech.zodiac.fortune.domain.Constellation;
import com.youlintech.zodiac.fortune.service.IConstellationService;
import com.youlintech.zodiac.fortune.service.IMaterialLibraryService;
import com.youlintech.zodiac.quartz.service.FortuneJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author adc
 */
@Slf4j
@Service
public class FortuneJobServiceImpl implements FortuneJobService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IConstellationService constellationService;
    @Autowired
    private IMaterialLibraryService materialLibraryService;
    @Override
    public void start() {
        log.info("更新素材库任务开始执行时间：" + new Date());
        Map<Long, List<Long>> map = materialLibraryService.getMaterialLibraryListByConstellationId();
        redisCache.deleteObject("FortuneTask:FortuneJob:msg");
        // 遍历 map 的每个条目
        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            Long constellationId = entry.getKey();

            List<Long> materialIds = entry.getValue();
            //获取当前循环的星座对象,通过id
            Constellation constellation = constellationService.getById(constellationId);
            try {
                if (!materialIds.isEmpty() && materialIds.size() > 1) {
                    //获取老的素材id
                    Long oldMaterialId = constellation.getMeterialLibraryId();
                    //获取老的素材id的下标
                    int index = materialIds.indexOf(oldMaterialId);
                    //判断当前选择的素材不是第一个和最后一个，则更新素材id
                    if (index == 0) {
                        // 选择第二个素材
                        Long selectedNewMaterialId = materialIds.get(index + 1);
                        updateMaterialLibraryId(constellation, oldMaterialId, selectedNewMaterialId);
                        redisCache.setCacheObject("FortuneTask:FortuneJob:msg:ID:"+constellationId, "星座序号:"+ constellationId + "更新成功！materialId:" + selectedNewMaterialId);
                        continue;
                    }
                    //如果是最后一个则
                    if (index == materialIds.size() - 1) {
                        // 选择第一个素材
                        Long selectedNewMaterialId = materialIds.get(0);
                        redisCache.setCacheObject("FortuneTask:FortuneJob:msg:ID:"+constellationId, "星座序号:"+ constellationId + "更新成功！materialId:" + selectedNewMaterialId);
                        updateMaterialLibraryId(constellation, oldMaterialId, selectedNewMaterialId);
                        continue;
                    }
                    //如果不是第一个也不是最后一个，则选择下一个素材
                    updateMaterialLibraryId(constellation, oldMaterialId, materialIds.get(index + 1));
                    redisCache.setCacheObject("FortuneTask:FortuneJob:msg:ID:"+constellationId, "星座序号:"+ constellationId + "更新成功！materialId:" + materialIds.get(index + 1));
                } else if (materialIds.size() == 1) {
                    if ( constellation.getMeterialLibraryId().equals(materialIds.get(0)) ) {
                        redisCache.setCacheObject("FortuneTask:FortuneJob:msg:ID:"+constellationId, "星座序号:"+ constellationId +"无法更新,素材只有一条！！");
                        log.warn("星座{}素材只有一条，并以使用，更新失败", constellationId);
                        continue;
                    }
                    Long oldMaterialId = constellation.getMeterialLibraryId();
                    updateMaterialLibraryId(constellation, oldMaterialId, materialIds.get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void updateMaterialLibraryId(Constellation constellation,Long oldMaterialId, Long newMaterialId) {
        // 更新星座的当前素材ID
        constellation.setMeterialLibraryId(newMaterialId);
        constellationService.updateById(constellation);
        String materialIdKey = getMaterialIdKey(constellation.getId());
        // 更新素材为已使用,redis缓存现在正在使用的素材id
        redisCache.deleteObject(materialIdKey);
        redisCache.setCacheObject(materialIdKey, newMaterialId);
        //删除使用过的素材，通过素材id
        materialLibraryService.removeById(oldMaterialId);
    }
    private String getMaterialIdKey(Long constellationId) {
        return "constellation:" + constellationId.toString() + ":material_id";
    }
}
