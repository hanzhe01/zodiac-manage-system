package com.youlintech.zodiac.web.controller;

import com.youlintech.zodiac.web.entity.ResponseEntitys;
import com.youlintech.zodiac.web.utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="read more url")
@RestController
@RequestMapping("/url")
public class ReadMoreUrlController {
    @Autowired
    private RedisCache redisCache;
    @GetMapping("/getReadMoreUrl")
    @ApiOperation(value = "获取更多阅读链接", notes = "获取更多阅读链接")
    public ResponseEntitys<String> getReadMoreUrl() {
        String string = redisCache.getCacheObject("sys_config:constellation.readmore.url");

        ResponseEntitys<String> response = new ResponseEntitys<>();
        if (string == null) {
            response.setSuccess(false);
            response.setData(null);
            response.setCode("401");
            response.setMsg("ReadMoreUrl Is Null");
            return response;
        }
        response.setSuccess(true);
        response.setData(string);
        response.setCode("200");
        response.setMsg("ReadMoreUrl Is "+ string);
        return response;
    }

}
