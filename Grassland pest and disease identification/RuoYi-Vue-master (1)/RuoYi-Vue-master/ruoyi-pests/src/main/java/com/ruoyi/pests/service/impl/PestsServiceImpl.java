package com.ruoyi.pests.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.pests.bo.line;
import com.ruoyi.pests.bo.pie;
import com.ruoyi.pests.bo.radder;
import com.ruoyi.pests.bo.table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pests.mapper.PestsMapper;
import com.ruoyi.pests.domain.Pests;
import com.ruoyi.pests.service.IPestsService;

/**
 * 有害生物Service业务层处理
 * 
 * @author BeakerOwO
 * @date 2024-07-11
 */
@Service
public class PestsServiceImpl implements IPestsService 
{
    @Autowired
    private PestsMapper pestsMapper;

    /**
     * 查询有害生物
     * 
     * @param id 有害生物主键
     * @return 有害生物
     */
    @Override
    public Pests selectPestsById(Long id)
    {
        return pestsMapper.selectPestsById(id);
    }
    @Override
    public List<table> getCountNature() {
        return pestsMapper.getCountNature();
    }
    @Override
    public List<line> getCounttime() {
        return pestsMapper.getCounttime();
    }
    @Override
    public List<radder> getCountnumber() {
        return pestsMapper.getCountnumber();
    }
    @Override
    public List<pie> getCounttype() {
        return pestsMapper.getCounttype();
    }
    /**
     * 查询有害生物列表
     * 
     * @param pests 有害生物
     * @return 有害生物
     */
    @Override
    public List<Pests> selectPestsList(Pests pests)
    {
        return pestsMapper.selectPestsList(pests);
    }

    /**
     * 新增有害生物
     * 
     * @param pests 有害生物
     * @return 结果
     */
    @Override
    public int insertPests(Pests pests)
    {
        return pestsMapper.insertPests(pests);
    }

    /**
     * 修改有害生物
     * 
     * @param pests 有害生物
     * @return 结果
     */
    @Override
    public int updatePests(Pests pests)
    {
//        pests.setUpdateTime(DateUtils.getNowDate());
        return pestsMapper.updatePests(pests);
    }

    /**
     * 批量删除有害生物
     * 
     * @param ids 需要删除的有害生物主键
     * @return 结果
     */
    @Override
    public int deletePestsByIds(Long[] ids)
    {
        return pestsMapper.deletePestsByIds(ids);
    }

    /**
     * 删除有害生物信息
     * 
     * @param id 有害生物主键
     * @return 结果
     */
    @Override
    public int deletePestsById(Long id)
    {
        return pestsMapper.deletePestsById(id);
    }
}
