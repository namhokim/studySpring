package com.example.webfluxtest.fluxandmono;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * ref https://www.baeldung.com/java-reactor-map-flatmap
 */
public class MapVsFlatMap {
    public Flux<String> map(String... inputs) {
        Flux<String> inFlux = Flux.just(inputs);
        Function<String, String> mapper = String::toUpperCase;
        return inFlux.map(mapper);
    }

    public Flux<String> flatmap(String... inputs) {
        Flux<String> inFlux = Flux.just(inputs);
        Function<String, Publisher<String>> mapper = s -> Flux.just(s.toUpperCase().split(""));
        return inFlux.flatMap(mapper);
    }
}
