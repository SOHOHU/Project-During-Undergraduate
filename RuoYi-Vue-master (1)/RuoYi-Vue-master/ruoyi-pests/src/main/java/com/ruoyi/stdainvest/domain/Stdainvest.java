package com.ruoyi.stdainvest.domain;

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
 * 标准地调查对象 stdainvest
 * 
 * @author BeakerOwO
 * @date 2024-07-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stdainvest implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 调查ID */
    private Long id;

    /** 调查图片 */
    @Excel(name = "调查图片")
    private String image;

    /** 采集时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "采集时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date collectTime;

    /** 普查人员ID */
    private Long surveyUid;

    /** 普查人员姓名 */
    @Excel(name = "普查人员姓名")
    private String surveyUname;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String administrativeRegion;

    /** 调查方法 */
    @Excel(name = "调查方法")
    private String investigationMethod;




    /** 有害生物类型 */
    @Excel(name = "有害生物类型")
    private Integer type;

    /** 有害生物名称 */
    @Excel(name = "有害生物名称")
    private String name;

    /** 鉴定状态 */
    @Excel(name = "鉴定状态")
    private Integer identifyStatus;

    /** 鉴定人员ID */
    private Long identifyUid;

    /** 鉴定人姓名 */
    @Excel(name = "鉴定人姓名")
    private String identifyUname;

    /** 鉴定时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "鉴定时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date identifyTime;


}
