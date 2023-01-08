package com.github.mvukic.route

import com.github.mvukic.auth.AuthenticationTypes
import com.github.mvukic.exception.FirstException
import com.github.mvukic.service.ApiService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.routing() {
    routing {
        route("/api") {
            basic()
            otherApi()
            authenticated()
        }
    }
}

fun Route.basic() {
    get("/exception-1") {
        throw FirstException("FirstException")
    }
    get("/hello-world") {
        call.application.environment.log.info("hello world")
        call.respondText("Hello World!")
    }

    get("/serialization") {
        call.respond(mapOf("hello" to "world"))
    }
}

fun Route.otherApi() {
    val apiService by closestDI().instance<ApiService>()
    get("/todos") {
        call.respond(apiService.getTodos().first)
    }
}

fun Route.authenticated() {
    authenticate(AuthenticationTypes.basic) {
        get("/protected-basic") {
            val principal = call.principal<UserIdPrincipal>()!!
            call.respondText("Hello ${principal.name}")
        }
    }
    authenticate(AuthenticationTypes.bearer) {
        get("/protected-bearer") {
            val principal = call.principal<UserIdPrincipal>()!!
            call.respondText("Hello ${principal.name}")
        }
    }
}