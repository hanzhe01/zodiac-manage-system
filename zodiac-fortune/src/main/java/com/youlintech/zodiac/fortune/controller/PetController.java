package com.youlintech.zodiac.fortune.controller;

import com.youlintech.zodiac.common.annotation.Log;
import com.youlintech.zodiac.common.core.controller.BaseController;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.page.TableDataInfo;
import com.youlintech.zodiac.common.enums.BusinessType;
import com.youlintech.zodiac.common.utils.poi.ExcelUtil;
import com.youlintech.zodiac.fortune.domain.Pet;
import com.youlintech.zodiac.fortune.service.IPetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 白名单Controller
 *
 * @author ouyagn
 * @date 2024-07-25
 */
@Api(tags="白名单管理")
@RestController
@RequestMapping("/pet/pet")
public class PetController extends BaseController
{
    @Autowired
    private IPetService petService;

    /**
     * 查询白名单列表
     */
    @ApiOperation("查询白名单列表")
    @PreAuthorize("@ss.hasPermi('pet:pet:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pet pet)
    {
        startPage();
        List<Pet> list = petService.selectPetList(pet);
        return getDataTable(list);
    }

    /**
     * 导出白名单列表
     */
    @ApiOperation("导出白名单列表")
    @PreAuthorize("@ss.hasPermi('pet:pet:export')")
    @Log(title = "白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pet pet)
    {
        List<Pet> list = petService.selectPetList(pet);
        ExcelUtil<Pet> util = new ExcelUtil<Pet>(Pet.class);
        util.exportExcel(response, list, "白名单数据");
    }

    /**
     * 获取白名单详细信息
     */
    @ApiOperation("获取白名单详细信息")
    @PreAuthorize("@ss.hasPermi('pet:pet:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petService.getById(id));
    }

    /**
     * 新增白名单
     */
    @ApiOperation("新增白名单")
    @PreAuthorize("@ss.hasPermi('pet:pet:add')")
    @Log(title = "白名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pet pet)
    {
        return toAjax(petService.save(pet));
    }

    /**
     * 修改白名单
     */
    @ApiOperation("修改白名单")
    @PreAuthorize("@ss.hasPermi('pet:pet:edit')")
    @Log(title = "白名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pet pet)
    {
        return toAjax(petService.updateById(pet));
    }

    /**
     * 删除白名单
     */
    @ApiOperation("删除白名单")
    @PreAuthorize("@ss.hasPermi('pet:pet:remove')")
    @Log(title = "白名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 获取导入模版
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<Pet> util = new ExcelUtil<Pet>(Pet.class);
        util.importTemplateExcel(response, "设备白名单数据");
    }

    @Log(title = "设备白名单管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('pet:pet:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<Pet> util = new ExcelUtil<Pet>(Pet.class);
        List<Pet> userList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = petService.importUser(userList, operName);
        return success(message);
    }

}
