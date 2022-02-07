package hello.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) { //psvm
        SpringApplication.run(Application.class, args); //run 에 현 클래스의 이름을 넘겨주고 아규먼트를 넘겨줌.
    }
}

// 스프링 MVC 웹을 설정해야 하는데 (디스패처 서블릿, 리스너에 웹 어플리케이션 어떤 거 쓸지 설정해야 하고, bean 설정 파일도 제공하고, 어디서부터 읽어야 할지)
// 근데 그냥 현재 클래스만 만들었는데 어떻게 돌아갈까? 그게, EnableAutoConfiguration 과 밀접한 관계가 있음.
// 그리고 SpringApplication.run 을 하면 무슨 일이 일어나는 걸까? 어떻게 톰캣이 뜬 걸까? 를 이제 살펴볼 거야.

// 스프링 부트 프로젝트 구조는 메이븐 JAVA 기본 프로젝트와 동일.
// 자바 소스 코드 (src\main\java)
// (자바 소스 코드 제외 모든 파일) 리소스 (src\main\resource)
// 그리고 resource 기준으로 클래스 패스 root 로 하위 파일을 모두 참조할 수 있음.
// 테스트 코드 (src\test\java)
// 테스트 리소스 (src\test\resource)

// 딱 하나 스프링 부트에서 추천하는 클래스 위치가 있음. 현 클래스 main application 위치임. @SpringBootApplication 가 달려있는.
// 최상위 패키지 속 위치. 왜냐면 @SpringBootApplication 어노테이션이 ComponentScan 을 시작하기 때문임. (ComponentScan 은 뒤에서 설명)
// 패키지를 기준으로 bean 등록을 하는데 java 폴더 밑에 바로 위치 시키면 모든 패키지를 scan 하겠단 의미.
