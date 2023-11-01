package com.xunfos.samples.iv_concepts

import com.xunfos.samples.trace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

suspend fun main() {
    coroutineScope {
        launch {
            trace("No context defined. Uses parent context")
        }
        launch(Dispatchers.Default) {
            trace("Default, from 2 to N CPU Core threads")
        }
        launch(Dispatchers.IO) {
            trace("IO Dispatcher. Default min 64 threads")
        }
        launch(Dispatchers.IO.limitedParallelism(4)) {
            trace("Sliced Dispatcher, limiting the amount of available threads!")
        }
        launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
            trace("New thread Context, with custom thread pool")
        }
        // Available on Android
//        launch(Dispatchers.Main) {
//            trace("Main thread:")
//        }
    }
}