package com.learn.tylorwu.mybaitsplus.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private String id;

    /** 创建时间**/
    private LocalDateTime createdOn;
    /** 创建人id**/
    private String createdBy;
    /** 创建人名称**/
    private String createdName;
    /** 修改人名称**/
    private String modifiedName;
    /** 修改时间**/
    private LocalDateTime modifiedOn;
    /** 修改人id**/
    private String modifiedBy;
    /** 逻辑删除标记**/
    private Integer isDeleted;
}
