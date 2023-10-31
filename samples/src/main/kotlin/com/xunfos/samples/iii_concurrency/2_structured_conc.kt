package com.xunfos.samples.iii_concurrency

import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = withExecutionTime {
    val sum = concurrentSum()
    println("Total amount of fruit: $sum")
}

private suspend fun concurrentSum(): Int = coroutineScope {
    val amountOfBananas = async { calculateBananas() }
    val amountOfApples = async { calculateApples() }

    amountOfApples.await() + amountOfBananas.await()
}

private suspend fun calculateBananas(): Int {
    delay(1000)
    return 7
}

private suspend fun calculateApples(): Int {
    delay(1000)
    return 15
}
