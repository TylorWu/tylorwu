package com.learn.tylorwu.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class Demo01Consumer {
    @Bean
    public Consumer<String> demoConsumer() {
        return message -> log.info("[demoConsumer][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

//    @Bean
//    public Consumer<String> demoConsumer() {
//        return message -> {
//            log.info("[demoConsumer][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//            throw new RuntimeException("我就是故意抛出一个异常");
//        };
//    }

    /**
     * 局部处理就是针对指定的channel进行处理，需要定义一个处理异常的方法，并在该方法上添加@ServiceActivator注解，该注解有一个inputChannel属性，用于指定对哪个channel进行处理
     * 格式为{destination}.{group}.errors
     * @param errorMessage
     */
    @ServiceActivator(inputChannel = "DEMO-TOPIC-01.demo01-consumer-group-DEMO-TOPIC-01.errors")
    public void handleError(ErrorMessage errorMessage) {
        log.error("[handleError][payload：{}]", ExceptionUtils.getRootCauseMessage(errorMessage.getPayload()));
        log.error("[handleError][originalMessage：{}]", errorMessage.getOriginalMessage());
        log.error("[handleError][headers：{}]", errorMessage.getHeaders());
    }

//    /**
//     * 订阅全局错误 Channel的错误消息，实现全局异常处理。
//     *
//     * @param errorMessage
//     */
//    @StreamListener(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME)
//    public void globalHandleError(ErrorMessage errorMessage) {
//        log.error("[globalHandleError][payload：{}]", ExceptionUtils.getRootCauseMessage(errorMessage.getPayload()));
//        log.error("[globalHandleError][originalMessage：{}]", errorMessage.getOriginalMessage());
//        log.error("[globalHandleError][headers：{}]", errorMessage.getHeaders());
//    }
}
