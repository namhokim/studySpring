package io.spring.start.here.ch03

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Person {
    var name: String = "Ella"

    @Autowired
    var parrot: Parrot? = null
}
