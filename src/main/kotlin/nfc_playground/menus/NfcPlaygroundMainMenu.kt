package nfc_playground.menus

import kotlin_konsole.konsole.utils.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu
import kotlinx.coroutines.delay

class NfcPlaygroundMainMenu: MainMenu() {

    override val title: String = "NFC Playground"

    override val options: MutableList<String> = NfcPlaygroundMainMenu.options

    override val menuWidth: Int = this.menuWidth()

    override suspend fun kallback(userInput: Int): KonsoleMenu? {
        return when (userInput) {
            1 -> {
                pcsc2PollingLoop()
                val outputStreamEnd = KonsolePrinter.appendKonsoleOutput(this, "", "14443-A PCSC2 Layer 2 Polling End")
                KonsolePrinter.print(outputStreamEnd, newlineBefore = false, newlineAfter = true)
                null // Return null to get back out to KonsoleMenu
            }
            2 -> writeTagsMenu
            3 -> utilitiesMenu
            else -> null
        }
    }

    private suspend fun pcsc2PollingLoop() {
        val outputStreamStart = KonsolePrinter.prependKonsoleOutput(this, "", "14443-A PCSC2 Layer 2 Polling Begin")
        KonsolePrinter.print("$outputStreamStart\n", newlineBefore = true, newlineAfter = false)
        for (i in 1..10) {
            KonsolePrinter.print("Polling for iso 14443", textColor = this.outputStreamColors[5], newlineBefore = true, newlineAfter = true)
            delay(1000L)
            if (i == 10) {
                KonsolePrinter.print("", newlineBefore = true, textColor = this.outputStreamColors[5], newlineAfter = false)
            }
        }
    }



    companion object {
        private val utilitiesMenu: UtilitiesMenu = UtilitiesMenu()

        private val writeTagsMenu: WriteTagsMenu = WriteTagsMenu()

        private val options = arrayListOf(
            "Read Tags",
            "Write Tags",
            "Utilities",
        )
    }

}