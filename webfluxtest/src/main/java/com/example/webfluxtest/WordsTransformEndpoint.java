package com.example.webfluxtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WordsTransformEndpoint {

    /**
     * http://localhost:8080/tr/upper?word=apple
     * =>
     * APPLE
     */
    @GetMapping("/tr/upper")
    public Mono<String> transformUpper(@RequestParam String word) {
        return Mono.just(word)
                .map(String::toUpperCase);
    }

    /**
     * http://localhost:8080/tr/uppers?words[]=apple&words[]=banana&words[]=car
     * =>
     * APPLE&BANANA&CAR
     */
    @GetMapping("/tr/uppers")
    public Mono<String> transformUppers(@RequestParam(value = "words[]") String[] words) {
        return Flux.fromArray(words)
                .map(String::toUpperCase)
                .reduce((s1, s2) -> s1 + "&" + s2);
    }

}
