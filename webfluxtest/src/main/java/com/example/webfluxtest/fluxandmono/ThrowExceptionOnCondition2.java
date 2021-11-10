package com.example.webfluxtest.fluxandmono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ThrowExceptionOnCondition2 {
    public static void main(String[] args) {
        Mono.just(false)
                .map(it -> {
                    if (!it) {
                        throw new IllegalStateException("It must be true");
                    } else {
                        return it;
                    }
                })
                .map(Result::new)
//                .onErrorMap(it -> it)
                .subscribe(e -> log.info("onNext: {}", e));
    }
}

