package com.xunfos.samples.iii_concurrency

import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = withExecutionTime {
    coroutineScope {
        val fruitList = listOf(
            async { calculateBananas() },
            async { calculateApples() }
        )
        val sum = fruitList.awaitAll().sum()
        println("Sum: $sum")
    }
}

private suspend fun calculateBananas(): Int {
    delay(1000)
    return 7
}

private suspend fun calculateApples(): Int {
    delay(1000)
    return 15
}
