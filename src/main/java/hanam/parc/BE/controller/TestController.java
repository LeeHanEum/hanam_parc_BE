package hanam.parc.BE.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}