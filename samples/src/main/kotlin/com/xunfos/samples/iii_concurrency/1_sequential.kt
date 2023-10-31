package com.xunfos.samples.iii_concurrency

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.delay

suspend fun main() = withExecutionTime {
    val amountOfBananas = calculateBananas()
    val amountOfApples = calculateApples()

    trace("Total amount of fruit: ${amountOfApples + amountOfBananas}")
}

private suspend fun calculateBananas(): Int {
    delay(1000)
    return 7
}

private suspend fun calculateApples(): Int {
    delay(1000)
    return 15
}
