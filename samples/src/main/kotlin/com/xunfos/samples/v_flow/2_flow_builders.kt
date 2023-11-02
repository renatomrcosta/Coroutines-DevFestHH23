package com.xunfos.samples.v_flow

import com.xunfos.samples.trace
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    trace("flowOf builder")
    flowOf(1, 2, 3).collect { trace(it) }

    trace("asFlow builder")
    listOf(1, 2, 3).asFlow().collect { trace(it) }

    trace("Flow builder")
    flow {
        emit(1)
        emit(2)
        // Any code can go here!
        emit(3)
    }.collect { trace(it) }
}
