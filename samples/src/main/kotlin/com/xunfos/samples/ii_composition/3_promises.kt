package com.xunfos.samples.ii_composition

import com.xunfos.samples.withExecutionTime
import java.util.concurrent.CompletableFuture

private fun fetchUserDetails(userId: String): CompletableFuture<String> =
    CompletableFuture.supplyAsync {
        Thread.sleep(300)
        "Imagine some meaningful content we derive based on an User"
    }

private fun fetchUserPurchaseHistory(userId: String): CompletableFuture<List<Any>> =
    CompletableFuture.supplyAsync {
        Thread.sleep(300)
        listOf()
    }

private fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>): CompletableFuture<Unit> =
    CompletableFuture.supplyAsync {
        Thread.sleep(300)
    }

fun main() = withExecutionTime {
    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    val userDetailsFuture = fetchUserDetails(userId = userId)
    val userPurchaseHistoryFuture = fetchUserPurchaseHistory(userId = userId)

    CompletableFuture.allOf(
        userDetailsFuture,
        userPurchaseHistoryFuture
    ).join()

    compileUserPurchaseReport(
        purchaseHistory = userPurchaseHistoryFuture.join(),
        userDetails = userDetailsFuture.join()
    ).whenComplete { u, t ->
        println("compiling user purchase report complete")
    }.join()
}
