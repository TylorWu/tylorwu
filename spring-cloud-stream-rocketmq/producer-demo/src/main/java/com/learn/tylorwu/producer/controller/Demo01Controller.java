package com.learn.tylorwu.producer.controller;

import com.alibaba.fastjson.JSON;
import com.learn.tylorwu.producer.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/demo01")
@Slf4j
public class Demo01Controller {

    @Value("${bindingName.demo:demo-out-0}")
    private String demoBindingName;

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("/send")
    public boolean send() {
        //创建 Spring Message 对象
        Message<String> springMessage = MessageBuilder.withPayload(JSON.toJSONString(Demo01Message.randomData())).build();
        return streamBridge.send(demoBindingName, springMessage);
    }

    @GetMapping("/send_delay")
    public boolean sendDelay() {
        // 创建 Spring Message 对象
        Message<String> springMessage = MessageBuilder.withPayload(JSON.toJSONString(Demo01Message.randomData()))
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "3") // 设置延迟级别为 3，10 秒后消费。
                .build();
        // 发送消息
        boolean sendResult = streamBridge.send(demoBindingName, springMessage);
        log.info("[sendDelay][发送消息完成, 结果 = {}]", sendResult);
        return sendResult;
    }

    /**
     * 消息过滤，在消费端过滤tags ,被过滤掉的消息，后续是无法被消费掉了，效果和消费成功是一样的。
     *
     * @return
     */
    @GetMapping("/send_tag")
    public boolean sendTag() {
        for (String tag : new String[]{"tags1", "tags2", "tags3"}) {
            // 创建 Spring Message 对象
            Message<String> springMessage = MessageBuilder.withPayload(JSON.toJSONString(Demo01Message.randomData()))
                    .setHeader(MessageConst.PROPERTY_TAGS, tag) // 设置 Tag
                    .build();
            // 发送消息
            streamBridge.send(demoBindingName, springMessage);
        }
        return true;
    }

    /**
     * 发送 3 条顺序消息的 HTTP 接口,测试顺序消费
     *
     * @return
     */
    @GetMapping("/send_orderly")
    public boolean sendOrderly() {
        // 发送 3 条相同 id 的消息
        int id = new Random().nextInt();
        for (int i = 0; i < 3; i++) {
            Message<String> springMessage = MessageBuilder.withPayload(JSON.toJSONString(Demo01Message.randomData()))
                    .build();
            // 发送消息
            streamBridge.send(demoBindingName, springMessage);
        }
        return true;
    }

    @GetMapping("/send_transaction")
    public boolean sendTransaction() {
        // 创建 Spring Message 对象
        Args args = new Args().setArgs1(1).setArgs2("2");
        Message<String> springMessage = MessageBuilder.withPayload(JSON.toJSONString(Demo01Message.randomData()))
                .setHeader("args", JSON.toJSONString(args))
                .build();
        // 发送消息
        return streamBridge.send(demoBindingName, springMessage);
    }


    public static class Args {

        private Integer args1;

        private String args2;

        public Integer getArgs1() {
            return args1;
        }

        public Args setArgs1(Integer args1) {
            this.args1 = args1;
            return this;
        }

        public String getArgs2() {
            return args2;
        }

        public Args setArgs2(String args2) {
            this.args2 = args2;
            return this;
        }

        @Override
        public String toString() {
            return "Args{" +
                    "args1=" + args1 +
                    ", args2='" + args2 + '\'' +
                    '}';
        }

    }
}
