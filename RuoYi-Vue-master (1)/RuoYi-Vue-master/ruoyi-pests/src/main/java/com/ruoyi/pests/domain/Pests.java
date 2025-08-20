package com.ruoyi.pests.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 有害生物对象 pests
 * 
 * @author BeakerOwO
 * @date 2024-07-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pests implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 图片
     */
    @Excel(name = "图片")
    private String image;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private Long type;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 拉丁学名
     */
    @Excel(name = "拉丁学名")
    private String latinName;

    /**
     * 所属目类
     */
    @Excel(name = "所属目类")
    private String order;

    /**
     * 所属科类
     */
    @Excel(name = "所属科类")
    private String family;

    /**
     * 详细描述
     */
    @Excel(name = "详细描述")
    private String info;

    private LocalDateTime updateTime;

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

