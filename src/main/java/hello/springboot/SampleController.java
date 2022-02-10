package hello.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public EntityModel hello() {
        Hello hello = new Hello();
        hello.setPrefix("Hey");
        hello.setName("spring");
        //링크 정보 추가 하는 방법.
        EntityModel<Hello> entityModel = new EntityModel<>(hello);
        entityModel.add(linkTo(methodOn(SampleController.class).hello()).withSelfRel());

        return entityModel;
    }
}
