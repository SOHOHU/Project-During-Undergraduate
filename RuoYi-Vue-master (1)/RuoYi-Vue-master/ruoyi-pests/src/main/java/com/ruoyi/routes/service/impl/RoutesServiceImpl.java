package com.ruoyi.routes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.routes.domain.Surveypoints;
import com.ruoyi.routes.mapper.RoutesMapper;
import com.ruoyi.routes.domain.Routes;
import com.ruoyi.routes.service.IRoutesService;

/**
 * 踏查路线Service业务层处理
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Service
public class RoutesServiceImpl implements IRoutesService 
{
    @Autowired
    private RoutesMapper routesMapper;

    /**
     * 查询踏查路线
     * 
     * @param id 踏查路线主键
     * @return 踏查路线
     */
    @Override
    public Routes selectRoutesById(Long id)
    {
        return routesMapper.selectRoutesById(id);
    }

    /**
     * 查询踏查路线列表
     * 
     * @param routes 踏查路线
     * @return 踏查路线
     */
    @Override
    public List<Routes> selectRoutesList(Routes routes)
    {
        return routesMapper.selectRoutesList(routes);
    }

    /**
     * 新增踏查路线
     * 
     * @param routes 踏查路线
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRoutes(Routes routes)
    {
        int rows = routesMapper.insertRoutes(routes);
        insertSurveypoints(routes);
        return rows;
    }

    /**
     * 修改踏查路线
     * 
     * @param routes 踏查路线
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRoutes(Routes routes)
    {
        routesMapper.deleteSurveypointsByRouteId(routes.getId());
        insertSurveypoints(routes);
        return routesMapper.updateRoutes(routes);
    }

    /**
     * 批量删除踏查路线
     * 
     * @param ids 需要删除的踏查路线主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRoutesByIds(Long[] ids)
    {
        routesMapper.deleteSurveypointsByRouteIds(ids);
        return routesMapper.deleteRoutesByIds(ids);
    }

    /**
     * 删除踏查路线信息
     * 
     * @param id 踏查路线主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRoutesById(Long id)
    {
        routesMapper.deleteSurveypointsByRouteId(id);
        return routesMapper.deleteRoutesById(id);
    }

    /**
     * 新增踏查点信息
     * 
     * @param routes 踏查路线对象
     */
    public void insertSurveypoints(Routes routes)
    {
        List<Surveypoints> surveypointsList = routes.getSurveypointsList();
        Long id = routes.getId();
        if (StringUtils.isNotNull(surveypointsList))
        {
            List<Surveypoints> list = new ArrayList<Surveypoints>();
            for (Surveypoints surveypoints : surveypointsList)
            {
                surveypoints.setRouteId(id);
                list.add(surveypoints);
            }
            if (list.size() > 0)
            {
                routesMapper.batchSurveypoints(list);
            }
        }
    }
}
