package com.xunfos.samples.i_thread_comparison

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.coroutineScope

fun main() =
    withExecutionTime {
        runBlocking {
            supervisorScope {
                List(10) {
                    launch {
                        delay(1000)
                        if (it == 5) {
                            throw Exception("Something with coroutine Funky!")
                        }
                        println("Executed Job #$it")
                    }
                }
            }
        }
    }


