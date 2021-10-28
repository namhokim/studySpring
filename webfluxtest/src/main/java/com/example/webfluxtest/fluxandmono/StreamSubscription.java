package com.example.webfluxtest.fluxandmono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class StreamSubscription {
    public static void main(String[] args) {
        Flux.just("A", "B", "C")
                .subscribe(
                        data -> log.info("onNext: {}", data),
                        err -> { /* ignored */ },
                        () -> log.info("onComplete"));

        Flux.range(1, 100)
                .subscribe(
                        data -> log.info("onNext: {}", data),
                        err -> { /* ignored */ },
                        () -> log.info("onComplete"),
                        subscription -> {
                            subscription.request(4);
                            subscription.cancel();
                        }
                );
    }
}
