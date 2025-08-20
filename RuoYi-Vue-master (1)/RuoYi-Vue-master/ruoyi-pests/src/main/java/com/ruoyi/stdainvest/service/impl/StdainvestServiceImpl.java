package com.ruoyi.stdainvest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stdainvest.mapper.StdainvestMapper;
import com.ruoyi.stdainvest.domain.Stdainvest;
import com.ruoyi.stdainvest.service.IStdainvestService;

/**
 * 标准地调查Service业务层处理
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
@Service
public class StdainvestServiceImpl implements IStdainvestService 
{
    @Autowired
    private StdainvestMapper stdainvestMapper;

    /**
     * 查询标准地调查
     * 
     * @param id 标准地调查主键
     * @return 标准地调查
     */
    @Override
    public Stdainvest selectStdainvestById(Long id)
    {
        return stdainvestMapper.selectStdainvestById(id);
    }

    /**
     * 查询标准地调查列表
     * 
     * @param stdainvest 标准地调查
     * @return 标准地调查
     */
    @Override
    public List<Stdainvest> selectStdainvestList(Stdainvest stdainvest)
    {
        return stdainvestMapper.selectStdainvestList(stdainvest);
    }

    /**
     * 新增标准地调查
     * 
     * @param stdainvest 标准地调查
     * @return 结果
     */
    @Override
    public int insertStdainvest(Stdainvest stdainvest)
    {
        return stdainvestMapper.insertStdainvest(stdainvest);
    }

    /**
     * 修改标准地调查
     * 
     * @param stdainvest 标准地调查
     * @return 结果
     */
    @Override
    public int updateStdainvest(Stdainvest stdainvest)
    {
        return stdainvestMapper.updateStdainvest(stdainvest);
    }

    /**
     * 批量删除标准地调查
     * 
     * @param ids 需要删除的标准地调查主键
     * @return 结果
     */
    @Override
    public int deleteStdainvestByIds(Long[] ids)
    {
        return stdainvestMapper.deleteStdainvestByIds(ids);
    }

    /**
     * 删除标准地调查信息
     * 
     * @param id 标准地调查主键
     * @return 结果
     */
    @Override
    public int deleteStdainvestById(Long id)
    {
        return stdainvestMapper.deleteStdainvestById(id);
    }
}
