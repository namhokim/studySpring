package com.example.webfluxtest;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping(path = "/hello/{name}")
    public Mono<String> hello(@PathVariable Name name) {
        return Mono.just(name.name + " World");
    }

    @Getter
    public static class Name {
        private final String name;

        public Name(String name) {
            this.name = name;
        }
    }
}
