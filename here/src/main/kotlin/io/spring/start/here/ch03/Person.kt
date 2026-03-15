package io.spring.start.here.ch03

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
data class Person @Autowired constructor(
     var parrot: Parrot,
) {
    var name: String = "Ella"
}
