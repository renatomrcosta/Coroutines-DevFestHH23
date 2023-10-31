package com.xunfos.samples.ii_composition

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture

private fun fetchUserDetails(userId: String): CompletableFuture<String> =
    CompletableFuture.supplyAsync {
        Thread.sleep(100)
        "Imagine some meaningful content we derive based on an User"
    }

private fun fetchUserPurchaseHistory(userId: String): CompletableFuture<List<Any>> =
    CompletableFuture.supplyAsync {
        Thread.sleep(100)
        emptyList()
    }

private fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>): CompletableFuture<Unit> =
    CompletableFuture.supplyAsync {
        Thread.sleep(100)
        if (purchaseHistory.isEmpty()) {
            throw RuntimeException("Every user has for sure purchase at least one item!")
        }
    }

fun main() = withExecutionTime {
    trace("Compiling report")

    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    val userDetailsFuture = fetchUserDetails(userId = userId)
    val purchaseHistoryFuture = fetchUserPurchaseHistory(userId = userId)

    CompletableFuture.allOf(
        userDetailsFuture,
        purchaseHistoryFuture
    ).join()

    compileUserPurchaseReport(
        purchaseHistory = purchaseHistoryFuture.join(),
        userDetails = userDetailsFuture.join()
    ).exceptionally { exception ->
        trace("I have to log the exception")
        trace(exception.message)
    }.whenComplete { u, t ->
        trace("compiling user purchase report complete")
    }.join()
}
