package com.example.webfluxtest.fluxandmono;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ThrowExceptionOnCondition {
    public static void main(String[] args) {
        Mono.just(false)
                .filter(it -> it)
                .map(Result::new)
                .switchIfEmpty(Mono.error(new IllegalStateException("It must be true")))
                .subscribe(e -> log.info("onNext: {}", e));
    }
}

@Getter
@ToString
class Result {
    private final boolean result;

    Result(boolean result) {
        this.result = result;
    }
}
