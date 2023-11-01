package com.xunfos.samples.iv_concepts

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


private fun myBlockingClientCall(): Int {
    trace("Starting blocking function call")
    Thread.sleep(3000)
    trace("Finished blocking function call")
    return 42
}

private suspend fun myNonBlockingClientCall(n: Int): Int {
    trace("Starting NON blocking function call n: $n")
    delay(2000)
    trace("Finished NON blocking function call n: $n")
    return 10 * n
}
fun main() = withExecutionTime {
    runBlocking(Dispatchers.Default) {
        val block1 = async {
            myNonBlockingClientCall(1)
        }
        withContext(Dispatchers.IO) {
            trace(myBlockingClientCall())
        }
        val block2 = async {
            myNonBlockingClientCall(2)
        }
        trace(block1.await())
        trace(block2.await())
    }
}
