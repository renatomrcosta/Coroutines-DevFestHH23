package com.xunfos.samples.ii_composition

import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private suspend fun fetchUserDetails(userId: String): String {
    delay(300)
    return "Imagine some meaningful content we derive based on an User"
}

private suspend fun fetchUserPurchaseHistory(userId: String): List<Any> {
    delay(300)
    return listOf()
}

private suspend fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>) {
    delay(300)
}

suspend fun main() = withExecutionTime {
    println("Compiling report")
    withContext(context = Dispatchers.Default) {
        val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09"

        val userDetailsDeferred: Deferred<String> =
            async { fetchUserDetails(userId = userId) }
        val purchaseHistoryDeferred: Deferred<List<Any>> =
            async { fetchUserPurchaseHistory(userId = userId) }

        compileUserPurchaseReport(
            purchaseHistory = purchaseHistoryDeferred.await(),
            userDetails = userDetailsDeferred.await()
        )
        println("compiling user purchase report complete")
    }
}

