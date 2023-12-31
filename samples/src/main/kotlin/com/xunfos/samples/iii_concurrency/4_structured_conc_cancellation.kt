package com.xunfos.samples.iii_concurrency

import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = withExecutionTime {
    try {
        val sum = concurrentSum()
        println("Total amount of fruit: $sum")
    } catch (e: ArithmeticException) {
        println("Invalid maths happened")
    }
}

private suspend fun concurrentSum(): Int = coroutineScope {
    val amountOfBananas = async { calculateBananasForAReallyLongTime() }
    val amountOfApples = async { calculateApples() }

    amountOfApples.await() + amountOfBananas.await()
}

suspend fun calculateBananasForAReallyLongTime(): Int {
    try {
        delay(Long.MAX_VALUE)
        return 42
    } finally {
        println("Exiting calculateBananas function")
    }
}

private suspend fun calculateApples(): Int {
    delay(300)
    println("Error happened here")
    throw ArithmeticException()
}
