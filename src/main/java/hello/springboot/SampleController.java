package hello.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        throw new SampleException();
    }

    //컨트롤러 내부에서만 사용 가능. 전역으로 사용하려면 클래스를 새로 생성하고 컨트롤러 어드바이스를 붙여야 함.
//    @ExceptionHandler(SampleException.class)
//    public @ResponseBody SampleError sampleError(SampleException s) {
//        SampleError sampleError = new SampleError();
//        sampleError.setMessage("error.sample.key");
//        sampleError.setReason("IDK");
//        return sampleError;
//    }

}
