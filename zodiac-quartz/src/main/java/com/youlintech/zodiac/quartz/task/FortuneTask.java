package com.youlintech.zodiac.quartz.task;

import com.youlintech.zodiac.common.core.redis.RedisCache;
import com.youlintech.zodiac.common.utils.spring.SpringUtils;
import com.youlintech.zodiac.fortune.constant.RedisConstant;
import com.youlintech.zodiac.fortune.service.IConstellationService;
import com.youlintech.zodiac.fortune.service.IMaterialLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author oyhz
 * @date 2024/7/23
 */
@Component("FortuneTask")
@Slf4j
public class FortuneTask {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IMaterialLibraryService materialLibraryService = SpringUtils.getBean(IMaterialLibraryService.class);

    @Autowired
    private IConstellationService constellationService = SpringUtils.getBean(IConstellationService.class);


    public void updateMaterialLibrary() {

        LocalDateTime now = LocalDateTime.now();
        redisCache.setCacheObject(RedisConstant.AUTO_MATERIAL_LIBRARY_TIME_KEY,now);
        log.info("更新素材库任务开始执行时间：" + now);

        Map<Long, List<Long>> map = materialLibraryService.getMaterialLibraryListByConstellationId();

        map.forEach((constellationId, materialIds) -> {
            //索引
            String currentIndexKey = "constellation:" + constellationId + ":current_material_index";
            //素材idkey
            String materialIdKey = "constellation:" + constellationId + ":material_id";
//            Constellation constellation = constellationService.getById(constellationId);
            Integer meterialLibraryIdIndex = redisCache.getCacheObject(currentIndexKey);

            /**
             * 对俩种情况进行处理，meterialLibraryIdIndex为空，以及meterialLibraryIdIndex为空大于materialIds的长度
             */
            if (meterialLibraryIdIndex == null) {
//                constellation.setMeterialLibraryId(materialIds.get(0));
//                constellationService.updateById(constellation);
                redisCache.setCacheObject(materialIdKey, materialIds.get(0),2, TimeUnit.DAYS);
                redisCache.setCacheObject(currentIndexKey, 1);
                return;
            }

            if (meterialLibraryIdIndex > materialIds.size() - 1) {
                redisCache.setCacheObject(currentIndexKey, 0, 1, TimeUnit.DAYS);
                redisCache.setCacheObject(materialIdKey, materialIds.get(0));
                return;
            }
            /**
             * meterialLibraryIdIndex!=null   && meterialLibraryIdIndex
             */
            redisCache.setCacheObject(materialIdKey, materialIds.get(meterialLibraryIdIndex));
            redisCache.setCacheObject(currentIndexKey, meterialLibraryIdIndex + 1,1, TimeUnit.DAYS);
            Object cacheObject = redisCache.getCacheObject(materialIdKey);
        });
    }
}
