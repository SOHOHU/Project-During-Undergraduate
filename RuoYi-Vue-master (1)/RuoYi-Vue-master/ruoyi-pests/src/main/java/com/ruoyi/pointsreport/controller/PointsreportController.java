package com.ruoyi.pointsreport.controller;

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
import com.ruoyi.pointsreport.domain.Pointsreport;
import com.ruoyi.pointsreport.service.IPointsreportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 踏查点上报Controller
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@RestController
@RequestMapping("/pointsreport/pointsreport")
public class PointsreportController extends BaseController
{
    @Autowired
    private IPointsreportService pointsreportService;

    /**
     * 查询踏查点上报列表
     */
    @PreAuthorize("@ss.hasPermi('pointsreport:pointsreport:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pointsreport pointsreport)
    {
        startPage();
        List<Pointsreport> list = pointsreportService.selectPointsreportList(pointsreport);
        return getDataTable(list);
    }

    /**
     * 导出踏查点上报列表
     */
    @PreAuthorize("@ss.hasPermi('pointsreport:pointsreport:export')")
    @Log(title = "踏查点上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pointsreport pointsreport)
    {
        List<Pointsreport> list = pointsreportService.selectPointsreportList(pointsreport);
        ExcelUtil<Pointsreport> util = new ExcelUtil<Pointsreport>(Pointsreport.class);
        util.exportExcel(response, list, "踏查点上报数据");
    }

    /**
     * 获取踏查点上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('pointsreport:pointsreport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pointsreportService.selectPointsreportById(id));
    }

    /**
     * 新增踏查点上报
     */
    @PreAuthorize("@ss.hasPermi('pointsreport:pointsreport:add')")
    @Log(title = "踏查点上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pointsreport pointsreport)
    {
        return toAjax(pointsreportService.insertPointsreport(pointsreport));
    }

    /**
     * 修改踏查点上报
     */
    @PreAuthorize("@ss.hasPermi('pointsreport:pointsreport:edit')")
    @Log(title = "踏查点上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pointsreport pointsreport)
    {
        return toAjax(pointsreportService.updatePointsreport(pointsreport));
    }

    /**
     * 删除踏查点上报
     */
    @PreAuthorize("@ss.hasPermi('pointsreport:pointsreport:remove')")
    @Log(title = "踏查点上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pointsreportService.deletePointsreportByIds(ids));
    }
}
