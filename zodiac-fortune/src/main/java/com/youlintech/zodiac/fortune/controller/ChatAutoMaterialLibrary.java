package com.youlintech.zodiac.fortune.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.youlintech.zodiac.common.core.controller.BaseController;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.redis.RedisCache;
import com.youlintech.zodiac.common.utils.http.HttpUtils;
import com.youlintech.zodiac.common.utils.poi.ExcelUtil;
import com.youlintech.zodiac.fortune.constant.AutoUrlConstant;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;
import com.youlintech.zodiac.fortune.model.entity.AutoEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author oyhz
 */
@RestController
@RequestMapping("/send")
public class ChatAutoMaterialLibrary extends BaseController {

    @Autowired
    private RedisCache redisCache;

    @ApiOperation("自动生成素材")
    @PostMapping("/auto")
    public AjaxResult auto(@RequestBody(required = true)AutoEntity autoEntity) {
        Map<String, Object> map = BeanUtil.beanToMap(autoEntity);
        String params = HttpUtil.toParams(map);
        String responseString = HttpUtils.sendPost(AutoUrlConstant.AUTO_URL, params);
        // responseString的body转换为Java对象集合
        Map<String, Object> resultMap = JSON.parseObject(responseString, Map.class);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) resultMap.get("data");
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(dataList);
        List<MaterialLibrary> list = jsonArray.toJavaList(MaterialLibrary.class);
        redisCache.deleteObject(AutoUrlConstant.AUTO_MATERIAL_LIBRARY_KEY);
        redisCache.setCacheList(AutoUrlConstant.AUTO_MATERIAL_LIBRARY_KEY, list);
        if ((Boolean)resultMap.get("success")) {
            return AjaxResult.success((String) resultMap.get("msg"), dataList);
        }
        return AjaxResult.error((String) resultMap.get("msg"));
    }

    @ApiOperation("导出自动生成素材")
    @PostMapping("/AutoExport")
    public void export(HttpServletResponse response)
    {
        List<MaterialLibrary> list = redisCache.getCacheList(AutoUrlConstant.AUTO_MATERIAL_LIBRARY_KEY);
        if (list == null || list.size() == 0) {
            return;
        }
        ExcelUtil<MaterialLibrary> util = new ExcelUtil<MaterialLibrary>(MaterialLibrary.class);
        util.exportExcel(response, list, "自动生成素材数据预览");
    }

}
