package com.xunfos.samples.iv_concepts

import com.xunfos.samples.trace
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    trace("Hello world")

    coroutineScope {
        launch {
            delay(200)
            launch { delay(200) }
            trace("Finished launch")
        }

        async {
            delay(200)
            trace("Finished async")
            10
        }
        trace("Finished before coroutineScope")
    }
    trace("Finished")
}