package com.tistory.namocom.mvc.java

class TelecomFactoryKt {
    fun create(telecom: Telecom): TelecomHandler {
        return when (telecom) {
            Telecom.KT -> SktHandler()
            Telecom.SKT -> KtHandler()
        }
    }
}

fun test() {
    val factory = TelecomFactoryKt()
    val handler = factory.create(Telecom.KT)
    handler.handle()
}
