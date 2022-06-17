package com.learn.tylorwu.producer.listener;

import com.alibaba.fastjson.JSON;
import com.learn.tylorwu.producer.controller.Demo01Controller;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

@RocketMQTransactionListener(txProducerGroup = "test")
@Slf4j
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 从消息 Header 中解析到 args 参数，并使用 JSON 反序列化
        Demo01Controller.Args args = JSON.parseObject(msg.getHeaders().get("args", String.class),
                Demo01Controller.Args.class);
        // ... local transaction process, return rollback, commit or unknown
        log.info("[executeLocalTransaction][执行本地事务，消息：{} args：{}]", msg, args);
        //如果这里提交rollback会回滚半消息、unknown默认30秒会回调checkLocalTransaction回查事物状态
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return rollback, commit or unknown
        log.info("[checkLocalTransaction][回查消息：{}]", msg);
        return RocketMQLocalTransactionState.COMMIT;
    }

}
