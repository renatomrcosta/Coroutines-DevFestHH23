package com.xunfos.samples.v_flow

import com.xunfos.samples.trace
import com.xunfos.samples.withExecutionTime
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface SampleHttpClient {
    @GET("/delay/1")
    suspend fun delay(): Response<Any>
}

suspend fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://httpbin.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val httpClient = retrofit.create<SampleHttpClient>()

    withExecutionTime {
        coroutineScope {
            repeat(2) {
                launch {
                    val response = httpClient.delay()
                    trace("Response: ${response.body()}")
                }
            }
        }
    }
}