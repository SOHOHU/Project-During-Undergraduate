package com.ruoyi.stdainvest.controller;

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
import com.ruoyi.stdainvest.domain.Stdainvest;
import com.ruoyi.stdainvest.service.IStdainvestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标准地调查Controller
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
@RestController
@RequestMapping("/stdainvest/stdainvest")
public class StdainvestController extends BaseController
{
    @Autowired
    private IStdainvestService stdainvestService;

    /**
     * 查询标准地调查列表
     */
    @PreAuthorize("@ss.hasPermi('stdainvest:stdainvest:list')")
    @GetMapping("/list")
    public TableDataInfo list(Stdainvest stdainvest)
    {
        startPage();
        List<Stdainvest> list = stdainvestService.selectStdainvestList(stdainvest);
        return getDataTable(list);
    }

    /**
     * 导出标准地调查列表
     */
    @PreAuthorize("@ss.hasPermi('stdainvest:stdainvest:export')")
    @Log(title = "标准地调查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Stdainvest stdainvest)
    {
        List<Stdainvest> list = stdainvestService.selectStdainvestList(stdainvest);
        ExcelUtil<Stdainvest> util = new ExcelUtil<Stdainvest>(Stdainvest.class);
        util.exportExcel(response, list, "标准地调查数据");
    }

    /**
     * 获取标准地调查详细信息
     */
    @PreAuthorize("@ss.hasPermi('stdainvest:stdainvest:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stdainvestService.selectStdainvestById(id));
    }

    /**
     * 新增标准地调查
     */
    @PreAuthorize("@ss.hasPermi('stdainvest:stdainvest:add')")
    @Log(title = "标准地调查", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Stdainvest stdainvest)
    {
        return toAjax(stdainvestService.insertStdainvest(stdainvest));
    }

    /**
     * 修改标准地调查
     */
    @PreAuthorize("@ss.hasPermi('stdainvest:stdainvest:edit')")
    @Log(title = "标准地调查", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Stdainvest stdainvest)
    {
        return toAjax(stdainvestService.updateStdainvest(stdainvest));
    }

    /**
     * 删除标准地调查
     */
    @PreAuthorize("@ss.hasPermi('stdainvest:stdainvest:remove')")
    @Log(title = "标准地调查", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stdainvestService.deleteStdainvestByIds(ids));
    }
}
