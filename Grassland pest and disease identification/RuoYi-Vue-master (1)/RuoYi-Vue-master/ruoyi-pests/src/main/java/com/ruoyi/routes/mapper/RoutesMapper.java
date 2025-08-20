package com.ruoyi.routes.mapper;

import java.util.List;
import com.ruoyi.routes.domain.Routes;
import com.ruoyi.routes.domain.Surveypoints;
import org.springframework.stereotype.Repository;

/**
 * 踏查路线Mapper接口
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Repository
public interface RoutesMapper 
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
     * 删除踏查路线
     * 
     * @param id 踏查路线主键
     * @return 结果
     */
    public int deleteRoutesById(Long id);

    /**
     * 批量删除踏查路线
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoutesByIds(Long[] ids);

    /**
     * 批量删除踏查点
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSurveypointsByRouteIds(Long[] ids);
    
    /**
     * 批量新增踏查点
     * 
     * @param surveypointsList 踏查点列表
     * @return 结果
     */
    public int batchSurveypoints(List<Surveypoints> surveypointsList);
    

    /**
     * 通过踏查路线主键删除踏查点信息
     * 
     * @param id 踏查路线ID
     * @return 结果
     */
    public int deleteSurveypointsByRouteId(Long id);
}
