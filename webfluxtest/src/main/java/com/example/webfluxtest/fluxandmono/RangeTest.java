package com.example.webfluxtest.fluxandmono;

import reactor.core.publisher.Flux;

public class RangeTest {
    public static void main(String[] args) {
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        Flux.range(1, 100)
                .repeat()
                .collectList()
                .block();
    }
}
