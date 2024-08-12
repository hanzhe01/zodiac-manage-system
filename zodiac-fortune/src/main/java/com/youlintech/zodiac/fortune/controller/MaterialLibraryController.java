package com.youlintech.zodiac.fortune.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import com.youlintech.zodiac.common.annotation.Log;
import com.youlintech.zodiac.common.constant.HttpStatus;
import com.youlintech.zodiac.common.core.controller.BaseController;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.page.TableDataInfo;
import com.youlintech.zodiac.common.enums.BusinessType;
import com.youlintech.zodiac.common.utils.poi.ExcelUtil;
import com.youlintech.zodiac.fortune.domain.Constellation;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;
import com.youlintech.zodiac.fortune.model.vo.MaterialLibraryVO;
import com.youlintech.zodiac.fortune.service.IConstellationService;
import com.youlintech.zodiac.fortune.service.IMaterialLibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 素材管理Controller
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Api(tags="素材管理管理")
@RestController
@RequestMapping("/materiallibrary/materiallibrary")
public class MaterialLibraryController extends BaseController
{
    @Autowired
    private IMaterialLibraryService materialLibraryService;

    @Autowired
    private IConstellationService constellationService;

    /**
     * 查询素材管理列表
     */
    @ApiOperation("查询素材管理列表")
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaterialLibrary materialLibrary)
    {
        startPage();
        List<MaterialLibrary> list = materialLibraryService.selectMaterialLibraryList(materialLibrary);
        List<MaterialLibraryVO> materialLibraryVOS = BeanUtil.copyToList(list, MaterialLibraryVO.class);
        materialLibraryVOS.forEach(item->{
            Long constellationId = item.getConstellationId();
            Constellation constellation = constellationService.getById(constellationId);
            item.setConstellationName(constellation.getConstellationName());
            item.setBirthday(constellation.getBirthday());
            item.setWebUrl(constellation.getWebUrl());
        });
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(materialLibraryVOS);
        rspData.setTotal(new PageInfo(list).getTotal());
//        BeanUtil.copyProperties();
        return rspData;
    }

    /**
     * 导出素材管理列表
     */
    @ApiOperation("导出素材管理列表")
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:export')")
    @Log(title = "素材管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaterialLibrary materialLibrary)
    {
        List<MaterialLibrary> list = materialLibraryService.selectMaterialLibraryList(materialLibrary);
        ExcelUtil<MaterialLibrary> util = new ExcelUtil<MaterialLibrary>(MaterialLibrary.class);
        util.exportExcel(response, list, "素材管理数据");
    }

    /**
     * 获取素材管理详细信息
     */
    @ApiOperation("获取素材管理详细信息")
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        MaterialLibrary materialLibrary = materialLibraryService.getById(id);
        MaterialLibraryVO materialLibraryVO = BeanUtil.copyProperties(materialLibrary, MaterialLibraryVO.class);
        Constellation constellation = constellationService.getById(materialLibraryVO.getConstellationId());

        materialLibraryVO.setWebUrl(constellation.getWebUrl());
        materialLibraryVO.setConstellationName(constellation.getConstellationName());
        materialLibraryVO.setBirthday(constellation.getBirthday());
        return success(materialLibraryVO);
    }

    /**
     * 新增素材管理
     */
    @ApiOperation("新增素材管理")
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:add')")
    @Log(title = "素材管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaterialLibrary materialLibrary)
    {
        return toAjax(materialLibraryService.save(materialLibrary));
    }

    /**
     * 修改素材管理
     */
    @ApiOperation("修改素材管理")
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:edit')")
    @Log(title = "素材管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaterialLibrary materialLibrary)
    {
        return toAjax(materialLibraryService.updateById(materialLibrary));
    }

    /**
     * 删除素材管理
     */
    @ApiOperation("删除素材管理")
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:remove')")
    @Log(title = "素材管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)

    {
        List<Long> idList = Arrays.asList(ids);
        for (Long id : ids) {
            MaterialLibrary materialLibrary = materialLibraryService.getById(id);
            Constellation constellation = constellationService.getById(materialLibrary.getConstellationId());
            if (Objects.equals(id, constellation.getMeterialLibraryId())) {
                return AjaxResult.warn("该素材已被星座使用，无法删除");
            }
        }
        return toAjax(materialLibraryService.removeByIds(idList));
    }


    /**
     * 获取素材管理导入模板
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<MaterialLibrary> util = new ExcelUtil<MaterialLibrary>(MaterialLibrary.class);
        util.importTemplateExcel(response, "素材数据");
    }

    /**
     * 批量导入素材数据
     * @param file
     * @return
     * @throws Exception
     */
    @Log(title = "素材管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('materiallibrary:materiallibrary:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<MaterialLibrary> util = new ExcelUtil<MaterialLibrary>(MaterialLibrary.class);
        List<MaterialLibrary> materialLibraryList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = materialLibraryService.importMaterialLibrary(materialLibraryList, operName);
        return success(message);
    }

}
