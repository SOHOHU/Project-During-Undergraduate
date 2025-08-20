package com.ruoyi.pests.mapper;

import java.util.List;

import com.ruoyi.pests.bo.line;
import com.ruoyi.pests.bo.pie;
import com.ruoyi.pests.bo.radder;
import com.ruoyi.pests.bo.table;
import com.ruoyi.pests.domain.Pests;
import org.springframework.stereotype.Repository;

/**
 * 有害生物Mapper接口
 * 
 * @author BeakerOwO
 * @date 2024-07-11
 */
@Repository
public interface PestsMapper 
{
    /**
     * 查询有害生物
     * 
     * @param id 有害生物主键
     * @return 有害生物
     */
    List<radder> getCountnumber();
    List<pie> getCounttype();
    List<table> getCountNature();
    List<line> getCounttime();
    public Pests selectPestsById(Long id);

    /**
     * 查询有害生物列表
     * 
     * @param pests 有害生物
     * @return 有害生物集合
     */
    public List<Pests> selectPestsList(Pests pests);

    /**
     * 新增有害生物
     * 
     * @param pests 有害生物
     * @return 结果
     */
    public int insertPests(Pests pests);

    /**
     * 修改有害生物
     * 
     * @param pests 有害生物
     * @return 结果
     */
    public int updatePests(Pests pests);

    /**
     * 删除有害生物
     * 
     * @param id 有害生物主键
     * @return 结果
     */
    public int deletePestsById(Long id);

    /**
     * 批量删除有害生物
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePestsByIds(Long[] ids);
}
