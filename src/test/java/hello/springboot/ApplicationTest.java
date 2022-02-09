package hello.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/test.properties")
public class ApplicationTest {

    @Autowired
    Environment environment;

    @Test
    public void contextLoads() {
        assertThat(environment.getProperty("boot.name"))
                .isEqualTo("heehee"); //properties에 정의된 값

    }
}