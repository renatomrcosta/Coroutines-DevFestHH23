package com.xunfos.samples.ii_composition

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.delay

private suspend fun fetchUserDetails(userId: String): String {
    trace("Welcome to my nice function!")
    delay(100)
    return "Imagine some meaningful content we derive based on an User"
}

private suspend fun fetchUserPurchaseHistory(userId: String): List<Any> {
    delay(100)
    return listOf()
}

private suspend fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>) {
    delay(100)
}

suspend fun main() = withExecutionTime {
    trace("Compiling report")
    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    val userDetails = fetchUserDetails(userId = userId)
    val purchaseHistory = fetchUserPurchaseHistory(userId = userId)

    compileUserPurchaseReport(
        userDetails = userDetails,
        purchaseHistory = purchaseHistory
    )

    trace("compiling user purchase report complete")
}