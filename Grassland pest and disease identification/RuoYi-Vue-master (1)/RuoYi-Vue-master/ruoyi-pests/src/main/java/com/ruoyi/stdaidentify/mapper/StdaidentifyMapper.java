package com.ruoyi.stdaidentify.mapper;

import java.util.List;
import com.ruoyi.stdaidentify.domain.Stdaidentify;
import org.springframework.stereotype.Repository;

/**
 * 标准地鉴定Mapper接口
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
@Repository
public interface StdaidentifyMapper 
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
     * 删除标准地鉴定
     * 
     * @param id 标准地鉴定主键
     * @return 结果
     */
    public int deleteStdaidentifyById(Long id);

    /**
     * 批量删除标准地鉴定
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdaidentifyByIds(Long[] ids);
}
