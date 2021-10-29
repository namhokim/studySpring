package com.example.webfluxtest.fluxandmono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 스트림 내용 캐싱하기 p.175
 */
@Slf4j
public class CachingElementsOfAStream {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> source = Flux.range(0, 2)                              // (1)
                .doOnSubscribe(s ->
                        log.info("new subscription for the cold publisher"));

        Flux<Integer> cachedSource = source.cache(Duration.ofSeconds(1));    // (2)

        cachedSource.subscribe(e -> log.info("[S 1] onNext: {}", e));        // (3)
        cachedSource.subscribe(e -> log.info("[S 2] onNext: {}", e));        // (4)

        Thread.sleep(1200);                                                  // (5)

        cachedSource.subscribe(e -> log.info("[S 3] onNext: {}", e));
    }
}
