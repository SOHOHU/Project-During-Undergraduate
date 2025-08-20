package com.ruoyi.routeapplys.mapper;

import java.util.List;
import com.ruoyi.routeapplys.domain.Routeapplys;
import org.springframework.stereotype.Repository;

/**
 * 路线审批Mapper接口
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Repository
public interface RouteapplysMapper 
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
     * 删除路线审批
     * 
     * @param id 路线审批主键
     * @return 结果
     */
    public int deleteRouteapplysById(Long id);

    /**
     * 批量删除路线审批
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRouteapplysByIds(Long[] ids);
}
