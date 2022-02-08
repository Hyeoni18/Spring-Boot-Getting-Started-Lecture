package hello.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import hello.springboot.Holoman;

@Component
public class HolomanRunner implements ApplicationRunner {
//아규먼트 인자 받아서 작업하고 싶을 때, 스프링 애플리케이션이 만들어지고 자동으로 실행되는 bean 만들고 싶다. 그럴 때 사용.

    @Autowired
    Holoman holoman; //현 프로젝트에 holoman을 bean으로 직접 등록하지 않음.

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoman);
    }
}
