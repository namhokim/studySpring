package com.example.webfluxtest.fluxandmono;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static reactor.function.TupleUtils.function;

@Slf4j
public class DeStructuringATuple {

    public static void main(String[] args) {
        Mono.zip(Mono.just("a"), Mono.just(2), Mono.just('b'))
                .map(function((s, count, c) -> new DeStrResult(s)))
                .subscribe(e -> log.info("onNext: {}", e));
    }

    @Getter
    @ToString
    static class DeStrResult {
        private final String result;
        DeStrResult(String result) {
            this.result = result;
        }
    }

}
