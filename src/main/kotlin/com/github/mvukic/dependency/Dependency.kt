package com.github.mvukic.dependency

import com.github.mvukic.service.ApiService
import com.github.mvukic.service.HttpClientService
import io.ktor.server.application.*
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun Application.dependencies() {
    di {
        bind { singleton { HttpClientService() } }
        bind { singleton { ApiService(instance()) } }
    }
}