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
        RestTemplate restTemplate = restTemplateBuilder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //동기
        //TODO /hello 호출
        String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
        System.out.println("R : "+helloResult);
        //TODO /world 호출
        String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
        System.out.println("R : "+worldResult);

        stopWatch.stop();
        System.out.println("R : "+stopWatch.prettyPrint());

        WebClient webClient = builder.build();
        stopWatch.start();

        //비동기
        //Webflux와 Mono에 대한 학습이 필요. 대충 Streaming API 인데. 스트림을 서브스크라이브 하기 전까지는 스트림이 흐르지 않음. 물이 고여있다 생각하면 됨.
        Mono<String> helloMono = webClient.get().uri("http://localhost:8080/hello").retrieve().bodyToMono(String.class);
        //칸막이를 열어줘야 흘러. 열어주자.
        helloMono.subscribe(s -> {
            System.out.println(s);

            if(stopWatch.isRunning()) {
                stopWatch.stop();
            }

            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        Mono<String> worldMono = webClient.get().uri("http://localhost:8080/world").retrieve().bodyToMono(String.class);
        worldMono.subscribe(s -> {
            System.out.println(s);

            if(stopWatch.isRunning()) {
                stopWatch.stop();
            }

            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }

}
