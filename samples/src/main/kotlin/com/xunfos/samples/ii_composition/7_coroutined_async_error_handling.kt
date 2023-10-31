package com.xunfos.samples.ii_composition

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.RuntimeException

private suspend fun fetchUserDetails(userId: String): String {
    delay(100)
    return "Imagine some meaningful content we derive based on an User"
}

private suspend fun fetchUserPurchaseHistory(userId: String): List<Any> {
    delay(100)
    return emptyList()
}

private suspend fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>) {
    delay(100)
    if (purchaseHistory.isEmpty()) {
        throw RuntimeException("Every user has for sure purchase at least one item!")
    }
}

suspend fun main() = withExecutionTime {
    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    trace("Compiling report")

    withContext(context = Dispatchers.IO) {
        val userDetailsDeferred: Deferred<String> = async { fetchUserDetails(userId = userId) }
        val purchaseHistoryDeferred: Deferred<List<Any>> = async { fetchUserPurchaseHistory(userId = userId) }

        try {
            compileUserPurchaseReport(
                purchaseHistory = purchaseHistoryDeferred.await(),
                userDetails = userDetailsDeferred.await()
            )
        } catch (exception: Exception) {
            trace("I have to log the exception")
            trace(exception.message)
        } finally {
            trace("compiling user purchase report complete")
        }
    }
}
