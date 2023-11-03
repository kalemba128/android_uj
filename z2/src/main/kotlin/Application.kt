package com.example

import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import database.DatabaseFactory
import org.apache.log4j.BasicConfigurator
import plugins.configureRouting
import plugins.configureSecurity

fun Application.module() {
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowHeader(HttpHeaders.ContentType)
    }
    DatabaseFactory.init()
    configureSecurity()
    configureSerialization()
    configureRouting()
}

fun main() {
    BasicConfigurator.configure();
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

