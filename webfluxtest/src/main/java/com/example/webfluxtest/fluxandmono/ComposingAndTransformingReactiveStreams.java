package com.example.webfluxtest.fluxandmono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.function.Function;

/**
 * 리액티브 스트림을 조합하고 변환하기 p.178
 */
@Slf4j
public class ComposingAndTransformingReactiveStreams {
    public static void main(String[] args) throws InterruptedException {
        Function<Flux<String>, Flux<String>> logUserInfo =                          // (1)
                stream -> stream                                                    //
                        .index()                                                    // (1.1)
                        .doOnNext(tp ->                                             // (1.2)
                                log.info("[{}] User: {}", tp.getT1(), tp.getT2()))  //
                        .map(Tuple2::getT2);                                        // (1.3)

        Flux.range(1000, 3)                                           // (2)
                .map(i -> "user-" + i)                                              //
                .transform(logUserInfo)                                             // (3)
                .subscribe(e -> log.info("onNext: {}", e));
    }
}
