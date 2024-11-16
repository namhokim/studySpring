package com.tistory.namocom.coverage.core.common.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Service

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Service
annotation class ApplicationService(@get:AliasFor(annotation = Service::class) val value: String = "")
