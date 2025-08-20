package com.ruoyi.pests.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * *业务类，根据页面业务不同设置不同属性，查询数据封装的类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessessBo {
    private String businessName;
    private String businessType;
}

