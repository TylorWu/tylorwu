package com.learn.tylorwu.start;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.learn.tylorwu")
@MapperScan("com.learn.tylorwu.mybatisplus.dao")
public class StartMain {
    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(StartMain.class);
            app.run(args);
            log.info("{}启动成功!", StartMain.class.getSimpleName());
        } catch (Exception e) {
            log.error("{}启动失败!", StartMain.class.getSimpleName(), e);
        }
    }
}
