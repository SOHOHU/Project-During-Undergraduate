package com.ruoyi.pointsreport.mapper;

import java.util.List;
import com.ruoyi.pointsreport.domain.Pointsreport;
import org.springframework.stereotype.Repository;

/**
 * 踏查点上报Mapper接口
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Repository
public interface PointsreportMapper 
{
    /**
     * 查询踏查点上报
     * 
     * @param id 踏查点上报主键
     * @return 踏查点上报
     */
    public Pointsreport selectPointsreportById(Long id);

    /**
     * 查询踏查点上报列表
     * 
     * @param pointsreport 踏查点上报
     * @return 踏查点上报集合
     */
    public List<Pointsreport> selectPointsreportList(Pointsreport pointsreport);

    /**
     * 新增踏查点上报
     * 
     * @param pointsreport 踏查点上报
     * @return 结果
     */
    public int insertPointsreport(Pointsreport pointsreport);

    /**
     * 修改踏查点上报
     * 
     * @param pointsreport 踏查点上报
     * @return 结果
     */
    public int updatePointsreport(Pointsreport pointsreport);

    /**
     * 删除踏查点上报
     * 
     * @param id 踏查点上报主键
     * @return 结果
     */
    public int deletePointsreportById(Long id);

    /**
     * 批量删除踏查点上报
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointsreportByIds(Long[] ids);
}
