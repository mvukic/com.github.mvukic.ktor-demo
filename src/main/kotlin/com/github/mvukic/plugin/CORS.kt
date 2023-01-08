package com.github.mvukic.plugin

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.cors() {
    install(CORS) {
        anyHost()
        methods.addAll(HttpMethod.DefaultMethods)
        allowOrigins { true }
        allowCredentials = true
        allowNonSimpleContentTypes = true
    }
}