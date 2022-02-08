package hello.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    //문제점 재현
    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setName("holong");
        holoman.setHowLong(10);
        return holoman;
    }
    //bean 등록 순서가 있음. 1차 componentScan 등록, 먼저 위 bean 등록을 함. 이후 autoConfiguration bean 등록. overriding 되면 위 bean은 덮여 사라짐.
    //스프링부트 2.1 부터는 동작 방법이 달라져 overriding error 발생. spring.main.allow-bean-definition-overriding=true setting 을 하든, @ConditionalOnMissingBean 어노테이션을 자동 설정 제공하는 쪽에 추가하면 됨.
}
