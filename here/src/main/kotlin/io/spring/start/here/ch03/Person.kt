package io.spring.start.here.ch03

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class Person @Autowired constructor(
    @Qualifier("parrot2") val parrot: Parrot,
) {
    var name: String = "Ella"
}
