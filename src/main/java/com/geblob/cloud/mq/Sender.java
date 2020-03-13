package com.geblob.cloud.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send() {
        amqpTemplate.convertAndSend("cloud", "got it");
    }
}
