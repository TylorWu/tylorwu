package com.learn.tylorwu.xxljob.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@RefreshScope //@RefreshScope 注解动态获取nacos配置
public class SystemConfig {
    /**
     * 组织结构多数据源统一的父节点id
     */
    @Value("${yl.xx.father.id:11b11db4-e907-4f1f-8835-b9daab6e1f23}")
    private String organizationFatherId;

    /**
     * 是否启动erp、ywy level自动转换
     */
    @Value("${yl.xx.level:true}")
    private boolean levelAutoConvert;
}
