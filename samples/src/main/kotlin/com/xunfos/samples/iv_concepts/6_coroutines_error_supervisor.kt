package com.xunfos.samples.iv_concepts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val timeMeasuredInMillis = measureTimeMillis {
            List(100_000) {
                launch {
                    delay(1000)
                    if (it == 2112) {
                        throw Exception("Something with coroutine Funky!")
                    }
                    println("Executed Job #$it")
                }
            }.forEach {
                it.join()
            }
        }
        println("Execution took ${timeMeasuredInMillis}ms")
    }
}
