package com.ruoyi.routeapplys.domain;

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
 * 路线审批对象 routeapplys
 * 
 * @author BeakerOwO
 * @date 2024-07-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Routeapplys implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 踏查路线ID */
    private Long id;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String administrativeRegion;

    /** 踏查点数量 */
    @Excel(name = "踏查点数量")
    private Long pointsNum;

    /** 踏查进度 */
    @Excel(name = "踏查进度")
    private Long progress;

    /** 申请用户ID */
    private Long uid;

    /** 申请用户姓名 */
    @Excel(name = "申请用户姓名")
    private String uname;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date applicationTime;

    /** 审批状态 */
    @Excel(name = "审批状态")
    private Long applicationStatus;

    /** 审批人ID */
    private Long approvalUid;

    /** 审批人姓名 */
    @Excel(name = "审批人姓名")
    private String approvalUname;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;
}
