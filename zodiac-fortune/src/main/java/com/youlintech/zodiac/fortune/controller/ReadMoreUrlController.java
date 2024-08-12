package com.youlintech.zodiac.fortune.controller;

import com.youlintech.zodiac.common.core.controller.BaseController;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.redis.RedisCache;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="read more url")
@RestController
@RequestMapping("/url")
public class ReadMoreUrlController extends BaseController {

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getMoreUrl")
    public AjaxResult getMoreUrl(){
        String url = redisCache.getCacheObject("sys_config:constellation.readmore.url");
        return success(url);
    }

}
