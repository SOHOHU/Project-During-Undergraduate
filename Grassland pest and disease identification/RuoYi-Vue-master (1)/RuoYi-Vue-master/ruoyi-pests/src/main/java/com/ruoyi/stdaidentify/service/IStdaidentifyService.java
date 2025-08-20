package com.ruoyi.stdaidentify.service;

import java.util.List;
import com.ruoyi.stdaidentify.domain.Stdaidentify;

/**
 * 标准地鉴定Service接口
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
public interface IStdaidentifyService 
{
    /**
     * 查询标准地鉴定
     * 
     * @param id 标准地鉴定主键
     * @return 标准地鉴定
     */
    public Stdaidentify selectStdaidentifyById(Long id);

    /**
     * 查询标准地鉴定列表
     * 
     * @param stdaidentify 标准地鉴定
     * @return 标准地鉴定集合
     */
    public List<Stdaidentify> selectStdaidentifyList(Stdaidentify stdaidentify);

    /**
     * 新增标准地鉴定
     * 
     * @param stdaidentify 标准地鉴定
     * @return 结果
     */
    public int insertStdaidentify(Stdaidentify stdaidentify);

    /**
     * 修改标准地鉴定
     * 
     * @param stdaidentify 标准地鉴定
     * @return 结果
     */
    public int updateStdaidentify(Stdaidentify stdaidentify);

    /**
     * 批量删除标准地鉴定
     * 
     * @param ids 需要删除的标准地鉴定主键集合
     * @return 结果
     */
    public int deleteStdaidentifyByIds(Long[] ids);

    /**
     * 删除标准地鉴定信息
     * 
     * @param id 标准地鉴定主键
     * @return 结果
     */
    public int deleteStdaidentifyById(Long id);

}
