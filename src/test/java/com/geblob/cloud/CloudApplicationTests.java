package com.geblob.cloud;

import com.geblob.cloud.mq.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudApplicationTests {
    @Autowired
    private Sender sender;

    @Test
    void contextLoads() {
        sender.send();
    }

}
