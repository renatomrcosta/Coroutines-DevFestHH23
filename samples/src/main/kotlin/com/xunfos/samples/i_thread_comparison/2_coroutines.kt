package com.xunfos.samples.i_thread_comparison

import com.xunfos.samples.trace
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // This block will bridge the blocking and non-blocking execution,
    // and create a coroutine scope
    runBlocking {
        // A list with a number of items will spawn.
        // Each item will initialize using the block of code below
        List(100) {
            launch {
                delay(1000)
                trace("Executed Job #$it")
            }
        }
    }
}
