package com.ruoyi.routes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 踏查点对象 surveypoints
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Surveypoints implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 踏查点ID */
    private Long id;

    /** 踏查路线ID */
    @Excel(name = "踏查路线ID")
    private Long routeId;

    /** 详细地点 */
    @Excel(name = "详细地点")
    private String detailLocation;
}
