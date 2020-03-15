package com.geblob.cloud.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class Sender {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void send()  {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            amqpTemplate.convertAndSend("cloud", "got it");
        }
    }
}
