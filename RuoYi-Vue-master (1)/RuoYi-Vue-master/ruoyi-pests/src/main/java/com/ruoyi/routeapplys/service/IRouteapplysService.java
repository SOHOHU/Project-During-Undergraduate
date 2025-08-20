package com.ruoyi.routeapplys.service;

import java.util.List;
import com.ruoyi.routeapplys.domain.Routeapplys;

/**
 * 路线审批Service接口
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
public interface IRouteapplysService 
{
    /**
     * 查询路线审批
     * 
     * @param id 路线审批主键
     * @return 路线审批
     */
    public Routeapplys selectRouteapplysById(Long id);

    /**
     * 查询路线审批列表
     * 
     * @param routeapplys 路线审批
     * @return 路线审批集合
     */
    public List<Routeapplys> selectRouteapplysList(Routeapplys routeapplys);

    /**
     * 新增路线审批
     * 
     * @param routeapplys 路线审批
     * @return 结果
     */
    public int insertRouteapplys(Routeapplys routeapplys);

    /**
     * 修改路线审批
     * 
     * @param routeapplys 路线审批
     * @return 结果
     */
    public int updateRouteapplys(Routeapplys routeapplys);

    /**
     * 批量删除路线审批
     * 
     * @param ids 需要删除的路线审批主键集合
     * @return 结果
     */
    public int deleteRouteapplysByIds(Long[] ids);

    /**
     * 删除路线审批信息
     * 
     * @param id 路线审批主键
     * @return 结果
     */
    public int deleteRouteapplysById(Long id);
}
