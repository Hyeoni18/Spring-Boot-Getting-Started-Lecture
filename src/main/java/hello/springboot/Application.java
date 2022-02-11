package hello.springboot;

import hello.springboot.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

@SpringBootApplication
public class Application {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoRepository mongoRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setEmail("ccc@ddd");
            account.setUsername("ccc");

//            mongoTemplate.insert(account);
            mongoRepository.insert(account);
            System.out.println("finished");

        };
    }
}
