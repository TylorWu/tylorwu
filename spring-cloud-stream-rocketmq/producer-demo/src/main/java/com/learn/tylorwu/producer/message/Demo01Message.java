package com.learn.tylorwu.producer.message;

import lombok.Data;

import java.util.Random;
import java.util.UUID;

@Data
public class Demo01Message {

    private Integer businessId;

    private String businessType;

    public static Demo01Message randomData() {
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setBusinessId(new Random().nextInt());
        demo01Message.setBusinessType(UUID.randomUUID().toString());
        return demo01Message;
    }
}
