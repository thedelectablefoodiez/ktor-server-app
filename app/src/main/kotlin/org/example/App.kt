package org.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.Serializable
import io.ktor.serialization.kotlinx.json.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/") {
                call.respondText("Welcome to your first Ktor server!")
            }
            get("/hello") {
                call.respond(mapOf("message" to "Hello, Ktor!"))
            }
        }
    }.start(wait = true)
}
