CREATE TABLE `ywy_resource_type`
(
    `id`                  char(36) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '主键',
    `resource_type_name`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源类型名称',
    `resource_type_level` tinyint(1)                                              NOT NULL DEFAULT '2' COMMENT '资源类型层级（2，3）',
    `resource_unit`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '' COMMENT '资源单位 (二级无单位，三级有单位)',
    `father_id`           char(36) CHARACTER SET utf8 COLLATE utf8_general_ci              DEFAULT NULL COMMENT '上级id，null表示二级',
    `created_by`          char(36) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL DEFAULT '' COMMENT '记录创建者id',
    `created_name`        char(36) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL DEFAULT '' COMMENT '记录创建者名称',
    `created_on`          datetime                                                         DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `modified_by`         char(36) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL DEFAULT '' COMMENT '记录修改者id',
    `modified_name`       char(36) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL DEFAULT '' COMMENT '记录修改者名称',
    `modified_on`         datetime                                                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录修改时间',
    `is_deleted`          tinyint(1)                                              NOT NULL DEFAULT '0' COMMENT '是否已删除,0 未删除 1 已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='资源类型表';