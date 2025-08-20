package com.ruoyi.routes.controller;

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
import com.ruoyi.routes.domain.Routes;
import com.ruoyi.routes.service.IRoutesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 踏查路线Controller
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@RestController
@RequestMapping("/routes/routes")
public class RoutesController extends BaseController
{
    @Autowired
    private IRoutesService routesService;

    /**
     * 查询踏查路线列表
     */
    @PreAuthorize("@ss.hasPermi('routes:routes:list')")
    @GetMapping("/list")
    public TableDataInfo list(Routes routes)
    {
        startPage();
        List<Routes> list = routesService.selectRoutesList(routes);
        return getDataTable(list);
    }

    /**
     * 导出踏查路线列表
     */
    @PreAuthorize("@ss.hasPermi('routes:routes:export')")
    @Log(title = "踏查路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Routes routes)
    {
        List<Routes> list = routesService.selectRoutesList(routes);
        ExcelUtil<Routes> util = new ExcelUtil<Routes>(Routes.class);
        util.exportExcel(response, list, "踏查路线数据");
    }

    /**
     * 获取踏查路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('routes:routes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(routesService.selectRoutesById(id));
    }

    /**
     * 新增踏查路线
     */
    @PreAuthorize("@ss.hasPermi('routes:routes:add')")
    @Log(title = "踏查路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Routes routes)
    {
        return toAjax(routesService.insertRoutes(routes));
    }

    /**
     * 修改踏查路线
     */
    @PreAuthorize("@ss.hasPermi('routes:routes:edit')")
    @Log(title = "踏查路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Routes routes)
    {
        return toAjax(routesService.updateRoutes(routes));
    }

    /**
     * 删除踏查路线
     */
    @PreAuthorize("@ss.hasPermi('routes:routes:remove')")
    @Log(title = "踏查路线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(routesService.deleteRoutesByIds(ids));
    }
}
