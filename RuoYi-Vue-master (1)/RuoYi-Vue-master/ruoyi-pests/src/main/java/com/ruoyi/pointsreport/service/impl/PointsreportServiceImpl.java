package com.ruoyi.pointsreport.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pointsreport.mapper.PointsreportMapper;
import com.ruoyi.pointsreport.domain.Pointsreport;
import com.ruoyi.pointsreport.service.IPointsreportService;

/**
 * 踏查点上报Service业务层处理
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Service
public class PointsreportServiceImpl implements IPointsreportService 
{
    @Autowired
    private PointsreportMapper pointsreportMapper;

    /**
     * 查询踏查点上报
     * 
     * @param id 踏查点上报主键
     * @return 踏查点上报
     */
    @Override
    public Pointsreport selectPointsreportById(Long id)
    {
        return pointsreportMapper.selectPointsreportById(id);
    }

    /**
     * 查询踏查点上报列表
     * 
     * @param pointsreport 踏查点上报
     * @return 踏查点上报
     */
    @Override
    public List<Pointsreport> selectPointsreportList(Pointsreport pointsreport)
    {
        return pointsreportMapper.selectPointsreportList(pointsreport);
    }

    /**
     * 新增踏查点上报
     * 
     * @param pointsreport 踏查点上报
     * @return 结果
     */
    @Override
    public int insertPointsreport(Pointsreport pointsreport)
    {
        return pointsreportMapper.insertPointsreport(pointsreport);
    }

    /**
     * 修改踏查点上报
     * 
     * @param pointsreport 踏查点上报
     * @return 结果
     */
    @Override
    public int updatePointsreport(Pointsreport pointsreport)
    {
        return pointsreportMapper.updatePointsreport(pointsreport);
    }

    /**
     * 批量删除踏查点上报
     * 
     * @param ids 需要删除的踏查点上报主键
     * @return 结果
     */
    @Override
    public int deletePointsreportByIds(Long[] ids)
    {
        return pointsreportMapper.deletePointsreportByIds(ids);
    }

    /**
     * 删除踏查点上报信息
     * 
     * @param id 踏查点上报主键
     * @return 结果
     */
    @Override
    public int deletePointsreportById(Long id)
    {
        return pointsreportMapper.deletePointsreportById(id);
    }
}
