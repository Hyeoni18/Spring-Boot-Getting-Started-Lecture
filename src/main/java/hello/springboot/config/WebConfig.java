package hello.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //리소스 핸들러 추가.
        registry.addResourceHandler("/m/**") // m 으로 시작하는 요청이오면
                .addResourceLocations("classpath:/m/") // 클래스패스 기준, m 디렉토리 밑에서 제공하겠다. 끝에 / 로 끝내야 함.
                .setCachePeriod(20); //초단위. 20초만.
    }

}
