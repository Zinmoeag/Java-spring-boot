package com.example.Spring.boot.learn;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping(path = "/helo")
    public String hello() {
        return "Hello this is the way";
    }
}
