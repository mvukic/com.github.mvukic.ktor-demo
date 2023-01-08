package com.github.mvukic.exception

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface KtorException {
    val status: HttpStatusCode
    val message: String
}

class FirstException(override val message: String) : KtorException, Throwable() {
    override val status = HttpStatusCode.BadRequest
}

@Serializable
data class ExceptionResponse(

    @SerialName("message")
    val message: String,

    @SerialName("id")
    val id: String
)

fun Application.exceptionHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is KtorException) {
                call.response.status(cause.status)
                call.respond(ExceptionResponse(cause.message, call.callId ?: ""))
            } else {
                call.respond(ExceptionResponse(cause.message ?: cause.cause?.message ?: "", call.callId ?: ""))
            }
        }
    }
}