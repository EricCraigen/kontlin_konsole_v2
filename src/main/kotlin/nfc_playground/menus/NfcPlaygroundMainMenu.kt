package nfc_playground.menus

import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu

class NfcPlaygroundMainMenu: MainMenu() {

    override val title: String = "NFC Playground"

    override val options: MutableList<String> = NfcPlaygroundMainMenu.options

    override fun kallback(userInput: Int): KonsoleMenu? {
        println("kalling back yo!")

        return when (userInput) {
            1 -> readTagsMenu
            2 -> writeTagsMenu
            else -> null
        }
    }

    companion object {

        private val readTagsMenu: ReadTagsMenu = ReadTagsMenu()

        private val writeTagsMenu: WriteTagsMenu = WriteTagsMenu()

        private val options = arrayListOf(
            "Read Tags",
            "Write Tags",
            "Utilities",
        )
    }

}