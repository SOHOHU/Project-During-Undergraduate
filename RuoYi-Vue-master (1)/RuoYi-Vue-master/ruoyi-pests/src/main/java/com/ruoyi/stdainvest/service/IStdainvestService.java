package com.ruoyi.stdainvest.service;

import java.util.List;
import com.ruoyi.stdainvest.domain.Stdainvest;

/**
 * 标准地调查Service接口
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
public interface IStdainvestService 
{
    /**
     * 查询标准地调查
     * 
     * @param id 标准地调查主键
     * @return 标准地调查
     */
    public Stdainvest selectStdainvestById(Long id);

    /**
     * 查询标准地调查列表
     * 
     * @param stdainvest 标准地调查
     * @return 标准地调查集合
     */
    public List<Stdainvest> selectStdainvestList(Stdainvest stdainvest);

    /**
     * 新增标准地调查
     * 
     * @param stdainvest 标准地调查
     * @return 结果
     */
    public int insertStdainvest(Stdainvest stdainvest);

    /**
     * 修改标准地调查
     * 
     * @param stdainvest 标准地调查
     * @return 结果
     */
    public int updateStdainvest(Stdainvest stdainvest);

    /**
     * 批量删除标准地调查
     * 
     * @param ids 需要删除的标准地调查主键集合
     * @return 结果
     */
    public int deleteStdainvestByIds(Long[] ids);

    /**
     * 删除标准地调查信息
     * 
     * @param id 标准地调查主键
     * @return 结果
     */
    public int deleteStdainvestById(Long id);
}
