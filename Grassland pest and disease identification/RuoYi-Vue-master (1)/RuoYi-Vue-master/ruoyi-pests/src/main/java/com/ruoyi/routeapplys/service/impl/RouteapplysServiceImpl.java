package com.ruoyi.routeapplys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.routeapplys.mapper.RouteapplysMapper;
import com.ruoyi.routeapplys.domain.Routeapplys;
import com.ruoyi.routeapplys.service.IRouteapplysService;

/**
 * 路线审批Service业务层处理
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Service
public class RouteapplysServiceImpl implements IRouteapplysService 
{
    @Autowired
    private RouteapplysMapper routeapplysMapper;

    /**
     * 查询路线审批
     * 
     * @param id 路线审批主键
     * @return 路线审批
     */
    @Override
    public Routeapplys selectRouteapplysById(Long id)
    {
        return routeapplysMapper.selectRouteapplysById(id);
    }

    /**
     * 查询路线审批列表
     * 
     * @param routeapplys 路线审批
     * @return 路线审批
     */
    @Override
    public List<Routeapplys> selectRouteapplysList(Routeapplys routeapplys)
    {
        return routeapplysMapper.selectRouteapplysList(routeapplys);
    }

    /**
     * 新增路线审批
     * 
     * @param routeapplys 路线审批
     * @return 结果
     */
    @Override
    public int insertRouteapplys(Routeapplys routeapplys)
    {
        return routeapplysMapper.insertRouteapplys(routeapplys);
    }

    /**
     * 修改路线审批
     * 
     * @param routeapplys 路线审批
     * @return 结果
     */
    @Override
    public int updateRouteapplys(Routeapplys routeapplys)
    {
        return routeapplysMapper.updateRouteapplys(routeapplys);
    }

    /**
     * 批量删除路线审批
     * 
     * @param ids 需要删除的路线审批主键
     * @return 结果
     */
    @Override
    public int deleteRouteapplysByIds(Long[] ids)
    {
        return routeapplysMapper.deleteRouteapplysByIds(ids);
    }

    /**
     * 删除路线审批信息
     * 
     * @param id 路线审批主键
     * @return 结果
     */
    @Override
    public int deleteRouteapplysById(Long id)
    {
        return routeapplysMapper.deleteRouteapplysById(id);
    }
}
