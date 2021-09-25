package com.tistory.namocom.mvc.external;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * from https://youtu.be/e3kgfcO0af4?t=2376 Spring for Architects
 */
@Service
public class FailingRiskClient {

    public String evaluate() {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (new Random().nextBoolean()) {
            throw new IllegalStateException();
        }

        return "OK";
    }
}
