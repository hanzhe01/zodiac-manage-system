package com.youlintech.zodiac.quartz.service;

import org.springframework.stereotype.Service;

/**
 * @author adc
 */

@Service
public interface FortuneJobService {

    /**
     * 启动定时任务
     * @return
     */
    public void start();
}
