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
    return emptyList()
}

private suspend fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>) {
    delay(300)
    if (purchaseHistory.isEmpty()) {
        throw RuntimeException("Every user has for sure purchased at least one item!")
    }
}

suspend fun main() = withExecutionTime {
    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    println("Compiling report")

    withContext(context = Dispatchers.IO) {
        val userDetailsDeferred: Deferred<String> = async { fetchUserDetails(userId = userId) }
        val purchaseHistoryDeferred: Deferred<List<Any>> = async { fetchUserPurchaseHistory(userId = userId) }

        try {
            compileUserPurchaseReport(
                purchaseHistory = purchaseHistoryDeferred.await(),
                userDetails = userDetailsDeferred.await()
            )
        } catch (exception: Exception) {
            println("I have to log the exception")
            println(exception.message)
        } finally {
            println("compiling user purchase report complete")
        }
    }
}
