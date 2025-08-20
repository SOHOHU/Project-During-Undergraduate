package com.ruoyi.stdaidentify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stdaidentify.mapper.StdaidentifyMapper;
import com.ruoyi.stdaidentify.domain.Stdaidentify;
import com.ruoyi.stdaidentify.service.IStdaidentifyService;

/**
 * 标准地鉴定Service业务层处理
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
@Service
public class StdaidentifyServiceImpl implements IStdaidentifyService 
{
    @Autowired
    private StdaidentifyMapper stdaidentifyMapper;

    /**
     * 查询标准地鉴定
     * 
     * @param id 标准地鉴定主键
     * @return 标准地鉴定
     */
    @Override
    public Stdaidentify selectStdaidentifyById(Long id)
    {
        return stdaidentifyMapper.selectStdaidentifyById(id);
    }

    /**
     * 查询标准地鉴定列表
     * 
     * @param stdaidentify 标准地鉴定
     * @return 标准地鉴定
     */
    @Override
    public List<Stdaidentify> selectStdaidentifyList(Stdaidentify stdaidentify)
    {
        return stdaidentifyMapper.selectStdaidentifyList(stdaidentify);
    }

    /**
     * 新增标准地鉴定
     * 
     * @param stdaidentify 标准地鉴定
     * @return 结果
     */
    @Override
    public int insertStdaidentify(Stdaidentify stdaidentify)
    {
        return stdaidentifyMapper.insertStdaidentify(stdaidentify);
    }

    /**
     * 修改标准地鉴定
     * 
     * @param stdaidentify 标准地鉴定
     * @return 结果
     */
    @Override
    public int updateStdaidentify(Stdaidentify stdaidentify)
    {
        return stdaidentifyMapper.updateStdaidentify(stdaidentify);
    }

    /**
     * 批量删除标准地鉴定
     * 
     * @param ids 需要删除的标准地鉴定主键
     * @return 结果
     */
    @Override
    public int deleteStdaidentifyByIds(Long[] ids)
    {
        return stdaidentifyMapper.deleteStdaidentifyByIds(ids);
    }

    /**
     * 删除标准地鉴定信息
     * 
     * @param id 标准地鉴定主键
     * @return 结果
     */
    @Override
    public int deleteStdaidentifyById(Long id)
    {
        return stdaidentifyMapper.deleteStdaidentifyById(id);
    }
}
