package com.ruoyi.pointsreport.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 踏查点上报对象 pointsreport
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pointsreport implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 踏查点ID */
    private Long id;

    /** 踏查路线ID */
    @Excel(name = "踏查路线ID")
    private Long routeId;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String administrativeRegion;

    /** 详细地点 */
    @Excel(name = "详细地点")
    private String detailLocation;

    /** 踏查状态 */
    @Excel(name = "踏查状态")
    private Long reportStatus;

    /** 踏查图片 */
    @Excel(name = "踏查图片")
    private String image;

    /** 踏查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "踏查时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reportTime;

    /** 踏查用户ID */
    private Long uid;

    /** 踏查用户姓名 */
    @Excel(name = "踏查用户姓名")
    private String uname;

}
