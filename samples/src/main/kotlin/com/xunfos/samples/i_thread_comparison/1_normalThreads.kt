package com.xunfos.samples.i_thread_comparison

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import kotlin.concurrent.thread

fun main() {
    withExecutionTime {
        // A list with a number of items will spawn.
        // Each item will initialize using the block of code below
        List(100) {
            thread {
                Thread.sleep(1000)
                trace("Executed Thread #$it")
            }
        }.forEach {
            // Wait until the thread spawned is done working before proceeding
            it.join()
        }
    }
}
