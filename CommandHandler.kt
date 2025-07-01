class CommandHandler {

    private var lastCommand: String? = null

    fun handleCommand(command: String) {
        if (command == lastCommand) {
            println("Command unchanged: $command, no action taken.")
            return
        }
        lastCommand = command

        when (command) {
            "vibrate" -> println("🔔 Vibrating...")
            "flashlight_on" -> println("💡 Flashlight turned ON")
            "flashlight_off" -> println("💡 Flashlight turned OFF")
            "ring" -> println("📞 Ringing...")
            else -> println("No command found.")
        }
    }
}
