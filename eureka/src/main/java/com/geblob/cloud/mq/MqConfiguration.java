package com.geblob.cloud.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfiguration {
    @Bean
    public Queue createQueue() {
        return new Queue("cloud");
    }
}
