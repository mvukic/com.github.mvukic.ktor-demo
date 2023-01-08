package com.github.mvukic.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*

fun Application.compression() {
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024)
        }
    }
}