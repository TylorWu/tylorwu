package com.learn.tylorwu.rocketmqdemo.producer;

import com.learn.tylorwu.rocketmqdemo.message.Demo04Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo04Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer id) {
        // 创建 Demo04Message 消息
        Demo04Message message = new Demo04Message();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(Demo04Message.TOPIC, message);
    }

}
