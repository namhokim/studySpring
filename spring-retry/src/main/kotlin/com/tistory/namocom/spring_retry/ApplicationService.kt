package com.tistory.namocom.spring_retry

import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.atomic.AtomicLong

@Service
class ApplicationService(
    private val domainService: DomainService,
) {
    private val counter: AtomicLong = AtomicLong(0)

    @Retryable(maxAttempts = 3, retryFor = [RuntimeException::class], recover = "recover")
    @Transactional
    fun run() {
        println("ApplicationService.run() - ${counter.incrementAndGet()}")
        domainService.somethingFail()
    }

    @Recover
    fun recover(e: RuntimeException) {
        println("ApplicationService.recover() - ${counter.get()}")
    }

}
