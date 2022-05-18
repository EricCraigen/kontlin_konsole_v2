@file:OptIn(ExperimentalUnsignedTypes::class)

package nfc_playground.menus

import jni_tester.menu.JniTesterMenu
import kotlin_konsole.konsole.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu
import kotlinx.coroutines.delay

class NfcPlaygroundMainMenu: MainMenu() {

    override val title: String = "NFC Playground"

    override val options: MutableList<String> = NfcPlaygroundMainMenu.options

    override suspend fun kallback(userInput: Int): KonsoleMenu? {
        return when (userInput) {
            1 -> {
                pcsc2PollingLoop() // Start polling loop...
                null
            }
            2 -> writeTagsMenu
            3 -> jniTesterMenu
            else -> null
        }
    }

    private suspend fun pcsc2PollingLoop() {
        KonsolePrinter.print("Polling for iso 14443", newlineBefore = true, newlineAfter = true)
        delay(1000L)
    }



    companion object {
        private val jniTesterMenu: JniTesterMenu = JniTesterMenu()

        private val writeTagsMenu: WriteTagsMenu = WriteTagsMenu()

        private val options = arrayListOf(
            "Read Tags",
            "Write Tags",
            "Jni Tester Main Menu",
        )
    }

}