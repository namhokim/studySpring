package com.example.webfluxtest.fluxandmono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapVsFlatMapTest {

    @Test
    void mapTest() {
        // given
        MapVsFlatMap sut = new MapVsFlatMap();

        // when
        final Flux<String> outFlux = sut.map("baeldung", ".", "com");

        // then
        StepVerifier.create(outFlux)
                .expectNext("BAELDUNG", ".", "COM")
                .expectComplete()
                .verify();
    }

    @Test
    void flatmapTest() {
        // given
        MapVsFlatMap sut = new MapVsFlatMap();
        List<String> output = new ArrayList<>();

        // when
        final Flux<String> outFlux = sut.flatmap("baeldung", ".", "com");
        outFlux.subscribe(output::add);

        // then
        assertThat(output).containsExactlyInAnyOrder("B", "A", "E", "L", "D", "U", "N", "G", ".", "C", "O", "M");
    }
}
