package com.youlintech.zodiac.fortune.controller;

import com.youlintech.zodiac.common.annotation.Log;
import com.youlintech.zodiac.common.core.controller.BaseController;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.page.TableDataInfo;
import com.youlintech.zodiac.common.enums.BusinessType;
import com.youlintech.zodiac.common.utils.poi.ExcelUtil;
import com.youlintech.zodiac.fortune.domain.Constellation;
import com.youlintech.zodiac.fortune.service.IConstellationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 星座管理Controller
 *
 * @author oyhz
 * @date 2024-07-17
 */
@Api(tags="星座管理管理")
@RestController
@RequestMapping("/constellation/constellation")
public class ConstellationController extends BaseController
{
    @Autowired
    private IConstellationService constellationService;

    /**
     * 查询星座管理列表
     */
    @ApiOperation("查询星座管理列表")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:list')")
    @GetMapping("/list")
    public TableDataInfo list(Constellation constellation)
    {
        startPage();
        List<Constellation> list = constellationService.selectConstellationList(constellation);
        return getDataTable(list);
    }

    /**
     * 导出星座管理列表
     */
    @ApiOperation("导出星座管理列表")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:export')")
    @Log(title = "星座管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Constellation constellation)
    {
        List<Constellation> list = constellationService.selectConstellationList(constellation);
        ExcelUtil<Constellation> util = new ExcelUtil<Constellation>(Constellation.class);
        util.exportExcel(response, list, "星座管理数据");
    }

    /**
     * 获取星座管理详细信息
     */
    @ApiOperation("获取星座管理详细信息")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(constellationService.getById(id));
    }

    /**
     * 新增星座管理
     */
    @ApiOperation("新增星座管理")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:add')")
    @Log(title = "星座管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Constellation constellation)
    {
        return constellationService.addConstellation(constellation);
    }

    /**
     * 修改星座管理
     */
    @ApiOperation("修改星座管理")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:edit')")
    @Log(title = "星座管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Constellation constellation)
    {
        return constellationService.updateConstellation(constellation);
    }

    /**
     * 删除星座管理
     */
    @ApiOperation("删除星座管理")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:remove')")
    @Log(title = "星座管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(constellationService.removeByIds(Arrays.asList(ids)));
    }

    @ApiOperation("获取星座预览信息")
    @GetMapping("/previewDetails")
    @PreAuthorize("@ss.hasPermi('constellation:constellation:showView')")
    public AjaxResult getPreviewDetails(@RequestParam(required = true,value = "materiallLibraryId") Long materiallLibraryId,
                                        @RequestParam(required = false,value = "pairingId") Long pairingId) {

        return constellationService.previewDetails(materiallLibraryId, pairingId);
    }
}
