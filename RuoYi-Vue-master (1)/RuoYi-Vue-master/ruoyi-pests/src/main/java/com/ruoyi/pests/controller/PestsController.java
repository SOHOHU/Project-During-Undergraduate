package com.ruoyi.pests.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.pests.domain.Pests;
import com.ruoyi.pests.service.IPestsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 有害生物Controller
 * 
 * @author BeakerOwO
 * @date 2024-07-11
 */
@RestController
@RequestMapping("/pests/pests")
public class PestsController extends BaseController
{
    @Autowired
    private IPestsService pestsService;

    /**
     * 查询有害生物列表
     */
    @PreAuthorize("@ss.hasPermi('pests:pests:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pests pests)
    {
        startPage();
        List<Pests> list = pestsService.selectPestsList(pests);
        return getDataTable(list);
    }


    @PreAuthorize("@ss.hasPermi('pests:pests:getCounttype')")
    @GetMapping("/getCounttype")
    public AjaxResult getCounttype()
    {
        return success(pestsService.getCounttype());
    }

    @PreAuthorize("@ss.hasPermi('pests:pests:getCountNature')")
    @GetMapping("/getCountNature")
    public AjaxResult getCountNature()
    {
        return success(pestsService.getCountNature());
    }
    @PreAuthorize("@ss.hasPermi('pests:pests:getCounttime')")
    @GetMapping("/getCounttime")
    public AjaxResult getCounttime()
    {
        return success(pestsService.getCounttime());
    }
    @PreAuthorize("@ss.hasPermi('pests:pests:getCountnumber')")
    @GetMapping("/getCountnumber")
    public AjaxResult getCountnumber()
    {
        return success(pestsService.getCountnumber());
    }


    /**
     * 导出有害生物列表
     */
    @PreAuthorize("@ss.hasPermi('pests:pests:export')")
    @Log(title = "有害生物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pests pests)
    {
        List<Pests> list = pestsService.selectPestsList(pests);
        ExcelUtil<Pests> util = new ExcelUtil<Pests>(Pests.class);
        util.exportExcel(response, list, "有害生物数据");
    }

    /**
     * 获取有害生物详细信息
     */
    @PreAuthorize("@ss.hasPermi('pests:pests:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pestsService.selectPestsById(id));
    }

    /**
     * 新增有害生物
     */
    @PreAuthorize("@ss.hasPermi('pests:pests:add')")
    @Log(title = "有害生物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pests pests)
    {
        return toAjax(pestsService.insertPests(pests));
    }

    /**
     * 修改有害生物
     */
    @PreAuthorize("@ss.hasPermi('pests:pests:edit')")
    @Log(title = "有害生物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pests pests)
    {
        return toAjax(pestsService.updatePests(pests));
    }

    /**
     * 删除有害生物
     */
    @PreAuthorize("@ss.hasPermi('pests:pests:remove')")
    @Log(title = "有害生物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pestsService.deletePestsByIds(ids));
    }
}
