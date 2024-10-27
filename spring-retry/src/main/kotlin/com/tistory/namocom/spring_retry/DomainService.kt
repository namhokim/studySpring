package com.tistory.namocom.spring_retry

import org.springframework.stereotype.Component

@Component
class DomainService {

    fun somethingFail() {
        throw RuntimeException("Fail")
    }

}
