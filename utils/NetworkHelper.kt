package app.utils

import java.net.HttpURLConnection
import java.net.URL

class NetworkHelper {
    companion object {
        const val BIN_URL = "https://api.jsonbin.io/v3/b/68622f2c8960c979a5b444fe/latest"
        const val API_KEY = "$2a$10$SBPRSMzgpGSpSx/8lbHp7OSy9WHsh1aAuayZZfIKDaYRfuPYopXau"
    }

    fun fetchCommand(): String? {
        try {
            val url = URL(BIN_URL)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("X-Master-Key", API_KEY)
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                println("HTTP error code: $responseCode")
                return null
            }

            val response = connection.inputStream.bufferedReader().use { it.readText() }

            // Extract "command" field from JSON response with simple regex
            val command = Regex("\"command\"\\s*:\\s*\"(\\w+)\"").find(response)?.groups?.get(1)?.value

            return command
        } catch (e: Exception) {
            println("Error fetching command: ${e.message}")
            return null
        }
    }
}
