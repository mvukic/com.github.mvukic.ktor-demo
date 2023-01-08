package com.github.mvukic.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*

object AuthenticationTypes {
    const val basic = "basic_authentication"
    const val bearer = "bearer_authentication"
}

fun Application.authentication() {
    // Authentication
    authentication {
        basic(name = AuthenticationTypes.basic) {
            realm = "Ktor Server"
            validate { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }

        bearer(name = AuthenticationTypes.bearer) {
            realm = "Ktor Server"
            authenticate { tokenCredential ->
                if (tokenCredential.token.isNotEmpty()) {
                    UserIdPrincipal(tokenCredential.token)
                } else {
                    null
                }
            }
        }
    }
}