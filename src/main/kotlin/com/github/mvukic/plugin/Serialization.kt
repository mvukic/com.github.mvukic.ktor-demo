package com.github.mvukic.plugin

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.serialization() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true })
    }
}