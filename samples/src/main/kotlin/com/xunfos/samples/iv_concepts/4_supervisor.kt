package com.xunfos.samples.iv_concepts

import com.xunfos.samples.trace
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

suspend fun main() {
    supervisorScope {
        val future = async {
            delay(200)
            error("Something went wrong")
            10
        }

        launch {
            delay(100)
            launch { delay(300) }
            trace("Finished launch")
        }

        trace("Launched all coroutines")
        trace("Result ${future.await()}")
    }
    trace("Finished")
}