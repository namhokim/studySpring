package com.tistory.findnamo.boot3.controller

import java.io.Serializable

data class TestEntity(
    val id: Long,
    val name: String,
): Serializable {
    companion object {
        @JvmStatic
        private val serialVersionUID = 1L
    }
}
