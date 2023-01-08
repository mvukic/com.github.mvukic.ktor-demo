package com.github.mvukic.plugin

import io.ktor.server.application.*

fun Application.customHeaderPlugin() {
    install(CustomHeaderPlugin) {
        headerName = "custom-header"
        headerValue = "test header"
    }
}

val CustomHeaderPlugin = createApplicationPlugin(
    name = "CustomHeaderPlugin",
    createConfiguration = ::CustomHeaderPluginConfiguration
) {
    val headerName = pluginConfig.headerName
    val headerValue = pluginConfig.headerValue
    pluginConfig.apply {
        onCall { call ->
            call.response.headers.append(headerName, headerValue)
        }
    }
}

class CustomHeaderPluginConfiguration {
    var headerName: String = "Custom-Header-Name"
    var headerValue: String = "Default value"
}