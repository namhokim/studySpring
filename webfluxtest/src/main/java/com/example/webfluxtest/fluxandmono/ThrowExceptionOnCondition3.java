package com.example.webfluxtest.fluxandmono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ThrowExceptionOnCondition3 {
    public static void main(String[] args) {
        Mono.just(false)
                .flatMap(it -> it
                        ? Mono.just(new Result(it))
                        : Mono.error(new IllegalStateException("It must be true")))
                .subscribe(e -> log.info("onNext: {}", e));
    }
}

