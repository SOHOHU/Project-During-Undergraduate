package com.ruoyi.routes.service;

import java.util.List;
import com.ruoyi.routes.domain.Routes;

/**
 * 踏查路线Service接口
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
public interface IRoutesService 
{
    /**
     * 查询踏查路线
     * 
     * @param id 踏查路线主键
     * @return 踏查路线
     */
    public Routes selectRoutesById(Long id);

    /**
     * 查询踏查路线列表
     * 
     * @param routes 踏查路线
     * @return 踏查路线集合
     */
    public List<Routes> selectRoutesList(Routes routes);

    /**
     * 新增踏查路线
     * 
     * @param routes 踏查路线
     * @return 结果
     */
    public int insertRoutes(Routes routes);

    /**
     * 修改踏查路线
     * 
     * @param routes 踏查路线
     * @return 结果
     */
    public int updateRoutes(Routes routes);

    /**
     * 批量删除踏查路线
     * 
     * @param ids 需要删除的踏查路线主键集合
     * @return 结果
     */
    public int deleteRoutesByIds(Long[] ids);

    /**
     * 删除踏查路线信息
     * 
     * @param id 踏查路线主键
     * @return 结果
     */
    public int deleteRoutesById(Long id);
}
