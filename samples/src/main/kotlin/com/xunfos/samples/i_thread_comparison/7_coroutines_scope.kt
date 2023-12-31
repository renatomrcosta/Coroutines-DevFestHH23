package com.xunfos.samples.i_thread_comparison

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import java.util.concurrent.Executors


fun main() = withExecutionTime {
    val myDispatcher =
        Executors.newFixedThreadPool(10).asCoroutineDispatcher()

    runBlocking {
        launch(Dispatchers.IO) {
            repeat(100_000) {
                launch {
                    delay(1000)
                    trace("Executed Job #$it")
                }
            }
        }
        launch(myDispatcher) {
            repeat(100_000) {
                launch {
                    delay(1000)
                    trace("Executed Job #$it")
                }
            }
        }
    }
    myDispatcher.close()
}


