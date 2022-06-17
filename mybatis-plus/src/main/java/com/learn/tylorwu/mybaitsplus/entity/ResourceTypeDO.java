package com.learn.tylorwu.mybaitsplus.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ywy_resource_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTypeDO extends BaseEntity implements Serializable {
    /**
     * 资源类型名称
     */
    private String resourceTypeName;

    /**
     * 资源类型层级（2，3）
     */
    private String resourceTypeLevel;

    /**
     * 资源单位 (二级无单位，三级有单位)
     */
    private String resourceUnit;

    /**
     * 资产名称
     */
    private String fatherId;
}
