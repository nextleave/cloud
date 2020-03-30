import com.geblob.cloud.EurekaApplication;
import com.geblob.cloud.mq.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EurekaApplication.class)
public class EurekaApplicationTests {
    @Autowired
    private Sender sender;

    @Test
    public void send() {
        sender.send();
    }

    @Test
    void contextLoads() {

    }
}
