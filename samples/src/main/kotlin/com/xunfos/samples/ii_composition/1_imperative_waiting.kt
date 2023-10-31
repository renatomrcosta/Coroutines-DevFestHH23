package com.xunfos.samples.ii_composition

import com.xunfos.samples.withExecutionTime

private fun fetchUserDetails(userId: String): String {
    Thread.sleep(100)
    return "Imagine some meaningful content we derive based on an User"
}

private fun fetchUserPurchaseHistory(userId: String): List<Any> {
    Thread.sleep(100)
    return listOf()
}

private fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>) {
    Thread.sleep(100)
}

fun main() = withExecutionTime { // Shorthand to print the time
    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    val userDetails = fetchUserDetails(userId = userId)
    val latestWebsiteVersion = fetchUserPurchaseHistory(userId = userId)

    compileUserPurchaseReport(userDetails = userDetails, purchaseHistory = latestWebsiteVersion)

    println("compiling user purchase report complete")
}
