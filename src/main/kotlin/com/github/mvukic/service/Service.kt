package com.github.mvukic.service

import com.github.mvukic.model.Todo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.java.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class HttpClientService {
    private val client = HttpClient(Java) {
        install(ContentNegotiation) {
            json()
        }
    }

    fun getClient() = client
}

class ApiService(
    private val httpClientService: HttpClientService
) {

    suspend fun getTodos() = coroutineScope {
        val client = httpClientService.getClient()
        val todos = async<List<Todo>> { client.get("https://jsonplaceholder.typicode.com/todos").body() }
        val dummy = async { delay(1500) }
        Pair(todos.await(), dummy.await())
    }
}