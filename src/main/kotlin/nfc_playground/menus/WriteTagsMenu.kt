package nfc_playground.menus

import kotlin_konsole.konsole.utils.KonsoleKolors
import kotlin_konsole.konsole.utils.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu
import kotlinx.coroutines.delay

class WriteTagsMenu: KonsoleMenu {

    override val title: String = "Write Tags Menu"

    override val options: MutableList<String> = WriteTagsMenu.options

    override val menuWidth: Int = this.menuWidth()

    override suspend fun kallback(userInput: Int): KonsoleMenu? {
        return when (userInput) {
            1 -> {
                val prompt = "${this.outputStreamColors[3]}\n\nPlace tag over reader..."
                val prependedPrompt = KonsolePrinter.prependKonsoleOutput(this, prompt, "Write Single Tag Start")
                KonsolePrinter.print(prependedPrompt, newlineBefore = true, newlineAfter = true)

                delay(1000L)

                val response = "Tag encoded!\n\n"
                val appendedResponse = KonsolePrinter.appendKonsoleOutput(this, response, "Write Single Tag End")
                KonsolePrinter.print(appendedResponse, textColor = this.outputStreamColors[5], newlineBefore = true, newlineAfter = true)
                null
            }
            else -> null
        }
    }

    companion object {

        private val options = arrayListOf(
            "Write Single",
            "Write In Auto Poll",
            "Write In List Passive Target",
            "Configure Writing Behavior",
        )
    }

}