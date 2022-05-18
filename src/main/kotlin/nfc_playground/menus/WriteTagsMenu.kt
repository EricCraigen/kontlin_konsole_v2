package nfc_playground.menus

import kotlin_konsole.konsole.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu
import kotlinx.coroutines.delay

class WriteTagsMenu: KonsoleMenu {

    override val title: String = "Write Tags Menu"

    override val options: MutableList<String> = WriteTagsMenu.options

    override suspend fun kallback(userInput: Int): KonsoleMenu? {
        return when (userInput) {
            1 -> {
                KonsolePrinter.print("Place tag over reader...", newlineBefore = true, newlineAfter = false)
                delay(1000L)
                KonsolePrinter.print("Tag encoded!", newlineBefore = true, newlineAfter = true)
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