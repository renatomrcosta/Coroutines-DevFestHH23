package com.xunfos.samples.ii_composition

import com.xunfos.samples.withExecutionTime

private fun <T> fetchUserDetails(userId: String, onComplete: (String) -> T) {
    Thread.sleep(300)
    onComplete("Imagine some meaningful content we derive based on an User")
}

private fun <T> fetchUserPurchaseHistory(userId: String, onComplete: (List<Any>) -> T) {
    Thread.sleep(300)
    onComplete(listOf())
}

private fun compileUserPurchaseReport(userDetails: Any, purchaseHistory: List<Any>, onComplete: () -> Unit) {
    Thread.sleep(300)
    onComplete()
}

fun main() = withExecutionTime {
    val userId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary userId
    fetchUserDetails(userId = userId) { userDetails ->
        fetchUserPurchaseHistory(userId = userId) { purchaseHistory ->
            compileUserPurchaseReport(purchaseHistory = purchaseHistory, userDetails = userDetails) {
                println("compiling user purchase report complete")
            }
        }
    }
    println("Hey, I'm out of the callbacks!")
}
