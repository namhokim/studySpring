package com.example.retryjava;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryableService {
    @Retryable(retryFor = RuntimeException.class)
    public void service() {
        System.out.println("... do something");
        throw new RuntimeException();
    }
    @Recover
    public void recover(RuntimeException e) {
        System.out.println("Panic!");
    }
}
