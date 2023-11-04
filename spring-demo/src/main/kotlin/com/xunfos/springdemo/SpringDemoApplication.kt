package com.xunfos.springdemo

import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx2.asFlow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootApplication
class SpringDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringDemoApplication>(*args)
}

@RestController
@RequestMapping("/user")
class MyController(
    private val userRepository: UserRepository,
    private val userPurchaseHistoryService: UserPurchaseHistoryService
) {
    @GetMapping("/")
    suspend fun listUsers(): Flow<UserDocument> {
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    suspend fun getUser(@PathVariable id: String): UserDocument {
        return userRepository.findById(id)
    }

    @GetMapping("/{id}/history")
    fun getUserPurchaseHistory(@PathVariable id: String): Flux<PurchaseHistory> {
        val history = userPurchaseHistoryService.getHistoryReactor(userId = id).asFlow()
        val history2 = userPurchaseHistoryService.getHistoryRxJava(userId = id).asFlow()
        return history
    }

    @GetMapping("/name")
    fun filterByName(@RequestParam("name") name: String): Mono<UserDocument> {
        return userRepository.findByName(name = name)
    }

    @GetMapping("/minimumScore")
    fun filterByScore(@RequestParam("minimumScore") score: Int): Flux<UserDocument> {
        return userRepository.findAllByScoreGreaterThan(score = score)
    }
}

@Document
data class UserDocument(val id: String, val name: String, val score: Int)

interface UserRepository : CoroutineCrudRepository<UserDocument, String> {
    suspend fun findByName(name: String): UserDocument
    fun findAllByScoreGreaterThan(score: Int): Flow<UserDocument>
}


data class PurchaseHistory(val id: String, val price: Int)

@Service
class UserPurchaseHistoryService {
    private val history = arrayOf(
        PurchaseHistory("1", 1),
        PurchaseHistory("2", 2),
    )

    fun getHistoryReactor(userId: String): Flux<PurchaseHistory> {
        return Flux.fromArray(history)
    }

    fun getHistoryRxJava(userId: String): Observable<PurchaseHistory> {
        return Observable.fromArray(*history)
    }
}