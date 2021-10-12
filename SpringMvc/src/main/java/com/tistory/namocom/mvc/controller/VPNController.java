package com.tistory.namocom.mvc.controller;

import com.tistory.namocom.mvc.controller.parameter.VpnRequest;
import com.tistory.namocom.mvc.controller.parameter.VpnResult;
import com.tistory.namocom.mvc.external.FailingRiskClient;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class VPNController {

    private final FailingRiskClient failingRiskClient;
    private final CircuitBreaker evaluate;

    public VPNController(FailingRiskClient failingRiskClient, CircuitBreakerFactory<Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration, Resilience4JConfigBuilder> circuitBreakerFactory) {
        this.failingRiskClient = failingRiskClient;

//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(5)
//                .waitDurationInOpenState(Duration.ofMillis(1_000))
//                .slidingWindowSize(2)
//                .build();
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofMillis(300))
//                .build();
//        circuitBreakerFactory.configureDefault(id -> new Resilience4JConfigBuilder("id")
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .timeLimiterConfig(timeLimiterConfig).build());

        this.evaluate = circuitBreakerFactory.create("evaluate");
    }

    @GetMapping(value = "/vpn/{requestNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VpnResult getVpn(VpnRequest vpnRequest) {
        return new VpnResult(vpnRequest);
    }

    @GetMapping("/evaluate")
    public String evaluate() {
        return evaluate.run(failingRiskClient::evaluate, throwable -> "NG");
    }

}
