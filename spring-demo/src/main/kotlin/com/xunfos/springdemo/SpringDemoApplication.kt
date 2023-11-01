package com.xunfos.springdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.random.Random

@SpringBootApplication
class SpringDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringDemoApplication>(*args)
}

@RestController
@RequestMapping("/user")
class MyController(private val userRepository: UserRepository) {
    init {
        println("Initializing Users")
        (1..20).forEach {
            userRepository.save(
                UserDocument(
                    id = "$it",
                    name = "Name $it",
                    score = Random.nextInt(0, 100)
                )
            ).block()
        }
    }

    @GetMapping("/")
    fun listUsers(): Flux<UserDocument> {
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): Mono<UserDocument> {
        return userRepository.findById(id)
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

interface UserRepository : ReactiveCrudRepository<UserDocument, String> {
    fun findByName(name: String): Mono<UserDocument>
    fun findAllByScoreGreaterThan(score: Int): Flux<UserDocument>
}