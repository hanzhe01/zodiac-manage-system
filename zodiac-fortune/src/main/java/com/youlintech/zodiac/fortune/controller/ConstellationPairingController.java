package com.youlintech.zodiac.fortune.controller;

import cn.hutool.core.bean.BeanUtil;
import com.youlintech.zodiac.common.annotation.Log;
import com.youlintech.zodiac.common.core.controller.BaseController;
import com.youlintech.zodiac.common.core.domain.AjaxResult;
import com.youlintech.zodiac.common.core.page.TableDataInfo;
import com.youlintech.zodiac.common.enums.BusinessType;
import com.youlintech.zodiac.common.utils.poi.ExcelUtil;
import com.youlintech.zodiac.fortune.domain.ConstellationPairing;
import com.youlintech.zodiac.fortune.enums.ZodiacEnum;
import com.youlintech.zodiac.fortune.model.vo.ConstellationPairingVO;
import com.youlintech.zodiac.fortune.service.IConstellationPairingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 星座配对Controller
 *
 * @author ouyang
 * @date 2024-07-19
 */
@Api(tags="星座配对管理")
@RestController
@RequestMapping("/pairing/pairing")
public class ConstellationPairingController extends BaseController
{
    @Autowired
    private IConstellationPairingService constellationPairingService;

    /**
     * 查询星座配对列表
     */
    @ApiOperation("查询星座配对列表")
    @PreAuthorize("@ss.hasPermi('pairing:pairing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConstellationPairing constellationPairing)
    {
        startPage();
        List<ConstellationPairing> list = constellationPairingService.selectConstellationPairingList(constellationPairing);
        List<ConstellationPairingVO> constellationPairingVOS = BeanUtil.copyToList(list, ConstellationPairingVO.class);
        constellationPairingVOS.forEach(constellationPairingVO -> {
            constellationPairingVO.setBePairedName(ZodiacEnum.getNameByCode(Math.toIntExact(constellationPairingVO.getBePairedId())));
            constellationPairingVO.setPairedName(ZodiacEnum.getNameByCode(Math.toIntExact(constellationPairingVO.getPairingId())));
            constellationPairingVO.setDateUpdated(constellationPairingVO.getUpdateTime());
        });
        return getDataTable(constellationPairingVOS);
    }

    /**
     * 导出星座配对列表
     */
    @ApiOperation("导出星座配对列表")
    @PreAuthorize("@ss.hasPermi('pairing:pairing:export')")
    @Log(title = "星座配对", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConstellationPairing constellationPairing)
    {
        List<ConstellationPairing> list = constellationPairingService.selectConstellationPairingList(constellationPairing);
        ExcelUtil<ConstellationPairing> util = new ExcelUtil<ConstellationPairing>(ConstellationPairing.class);
        util.exportExcel(response, list, "星座配对数据");
    }

    /**
     * 获取星座配对详细信息
     */
    @ApiOperation("获取星座配对详细信息")
    @PreAuthorize("@ss.hasPermi('pairing:pairing:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        ConstellationPairing pairing = constellationPairingService.getById(id);
        ConstellationPairingVO vo = BeanUtil.copyProperties(pairing, ConstellationPairingVO.class);
        vo.setBePairedName(ZodiacEnum.getNameByCode(Math.toIntExact(vo.getBePairedId())));
        vo.setPairedName(ZodiacEnum.getNameByCode(Math.toIntExact(vo.getPairingId())));
        return success(vo);
    }

    /**
     * 新增星座配对
     */
    @ApiOperation("新增星座配对")
    @PreAuthorize("@ss.hasPermi('pairing:pairing:add')")
    @Log(title = "星座配对", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConstellationPairing constellationPairing)
    {
        return toAjax(constellationPairingService.addOrUpdate(constellationPairing));
    }

    /**
     * 修改星座配对
     */
    @ApiOperation("修改星座配对")
    @PreAuthorize("@ss.hasPermi('pairing:pairing:edit')")
    @Log(title = "星座配对", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConstellationPairing constellationPairing)
    {
        LocalDateTime now = LocalDateTime.now();
        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        constellationPairing.setDateUpdated(date);
        return toAjax(constellationPairingService.updateById(constellationPairing));
    }

    /**
     * 删除星座配对
     */
    @ApiOperation("删除星座配对")
    @PreAuthorize("@ss.hasPermi('pairing:pairing:remove')")
    @Log(title = "星座配对", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(constellationPairingService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 获取导入模版
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ConstellationPairing> util = new ExcelUtil<ConstellationPairing>(ConstellationPairing.class);
        util.importTemplateExcel(response, "配对数据");
    }

    /**
     * 批量导入数据
     */
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('pairing:pairing:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ConstellationPairing> util = new ExcelUtil<ConstellationPairing>(ConstellationPairing.class);
        List<ConstellationPairing> constellationPairingList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = constellationPairingService.importPairing(constellationPairingList, updateSupport, operName);
        return success(message);
    }


}
