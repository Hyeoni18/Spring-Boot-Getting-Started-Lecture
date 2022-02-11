package hello.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestRunner implements ApplicationRunner {
    //클라이언트라고 생각.

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = builder
      //          .baseUrl("http://localhost:8080")
                .build();

        Mono<String> helloMono = webClient.get().uri("/hello").retrieve().bodyToMono(String.class); //간단하게 호출 가능.
        helloMono.subscribe(s -> {
            System.out.println(s);
        });
    }

}
