package com.youlintech.zodiac.quartz.task;

import com.youlintech.zodiac.quartz.service.FortuneJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author oyhz
 * @date 2024/7/23
 */
@Component("FortuneTask")
@Slf4j
public class FortuneTask {

    @Autowired
    private FortuneJobService fortuneJobService;

    public void updateMaterialLibrary() {
        fortuneJobService.start();
    }
}
