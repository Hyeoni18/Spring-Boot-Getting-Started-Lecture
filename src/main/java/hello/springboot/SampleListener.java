package hello.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(SampleListener.class);

    @Autowired
    private String hello;

    @Autowired
    private BootProperties bootProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("========================");
        logger.debug(hello);
        logger.debug(bootProperties.getName());
        logger.debug(bootProperties.getFullName());
        logger.debug("========================");
    }
}
