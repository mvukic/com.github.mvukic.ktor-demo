package com.github.mvukic

import com.github.mvukic.auth.authentication
import com.github.mvukic.dependency.dependencies
import com.github.mvukic.exception.exceptionHandler
import com.github.mvukic.logging.monitoringAndLogging
import com.github.mvukic.plugin.compression
import com.github.mvukic.plugin.cors
import com.github.mvukic.plugin.customHeaderPlugin
import com.github.mvukic.plugin.serialization
import com.github.mvukic.route.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        dependencies()
        exceptionHandler()
        customHeaderPlugin()
        monitoringAndLogging()
        compression()
        serialization()
        cors()
        authentication()
        routing()
    }.start(wait = true)
}
