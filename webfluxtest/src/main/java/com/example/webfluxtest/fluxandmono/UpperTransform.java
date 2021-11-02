package com.example.webfluxtest.fluxandmono;

import reactor.core.publisher.Flux;

public class UpperTransform {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("red", "white", "blue");

        Flux<String> upper = flux
                .log()
                .map(String::toUpperCase);
        upper.subscribe(System.out::println);
    }
}
