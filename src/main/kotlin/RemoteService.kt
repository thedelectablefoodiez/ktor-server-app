package app

import kotlinx.coroutines.*
import app.utils.NetworkHelper

fun main() {
    runBlocking {
        println("Launching Remote Control App...")
        RemoteService().start()
    }
}

class RemoteService {
    private var lastCommand: String? = null
    private val networkHelper = NetworkHelper()

    suspend fun start() {
        println("RemoteService started. Polling server...")

        while (true) {
            val command = networkHelper.fetchCommand()
            if (command == null) {
                println("📡 Failed to fetch command, retrying...")
                delay(2000)
                continue
            }

            if (command == lastCommand) {
                println("Command unchanged: $command, no action taken.")
            } else {
                when (command) {
                    "vibrate" -> {
                        println("🔔 Vibrating...")
                        // TODO: Add vibrate logic here
                    }
                    "stop" -> {
                        println("🛑 Stopping service")
                        break
                    }
                    "beep" -> {
                        println("🔔 Beep command received!")
                        // TODO: Add beep logic here
                    }
                    else -> {
                        println("No command found or unknown command.")
                    }
                }
                lastCommand = command
            }
            delay(3000) // Poll every 3 seconds
        }
        println("RemoteService stopped.")
    }
}
