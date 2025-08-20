package com.ruoyi.stdaidentify.controller;

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
import com.ruoyi.stdaidentify.domain.Stdaidentify;
import com.ruoyi.stdaidentify.service.IStdaidentifyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标准地鉴定Controller
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/stdaidentify/stdaidentify")
public class StdaidentifyController extends BaseController
{
    @Autowired
    private IStdaidentifyService stdaidentifyService;

    /**
     * 查询标准地鉴定列表
     */
    @PreAuthorize("@ss.hasPermi('stdaidentify:stdaidentify:list')")
    @GetMapping("/list")
    public TableDataInfo list(Stdaidentify stdaidentify)
    {
        startPage();
        List<Stdaidentify> list = stdaidentifyService.selectStdaidentifyList(stdaidentify);
        return getDataTable(list);
    }

    /**
     * 导出标准地鉴定列表
     */
    @PreAuthorize("@ss.hasPermi('stdaidentify:stdaidentify:export')")
    @Log(title = "标准地鉴定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Stdaidentify stdaidentify)
    {
        List<Stdaidentify> list = stdaidentifyService.selectStdaidentifyList(stdaidentify);
        ExcelUtil<Stdaidentify> util = new ExcelUtil<Stdaidentify>(Stdaidentify.class);
        util.exportExcel(response, list, "标准地鉴定数据");
    }

    /**
     * 获取标准地鉴定详细信息
     */
    @PreAuthorize("@ss.hasPermi('stdaidentify:stdaidentify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stdaidentifyService.selectStdaidentifyById(id));
    }

    /**
     * 新增标准地鉴定
     */
    @PreAuthorize("@ss.hasPermi('stdaidentify:stdaidentify:add')")
    @Log(title = "标准地鉴定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Stdaidentify stdaidentify)
    {
        return toAjax(stdaidentifyService.insertStdaidentify(stdaidentify));
    }

    /**
     * 修改标准地鉴定
     */
    @PreAuthorize("@ss.hasPermi('stdaidentify:stdaidentify:edit')")
    @Log(title = "标准地鉴定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Stdaidentify stdaidentify)
    {
        return toAjax(stdaidentifyService.updateStdaidentify(stdaidentify));
    }

    /**
     * 删除标准地鉴定
     */
    @PreAuthorize("@ss.hasPermi('stdaidentify:stdaidentify:remove')")
    @Log(title = "标准地鉴定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stdaidentifyService.deleteStdaidentifyByIds(ids));
    }
}
