package nfc_playground.menus

import kotlin_konsole.menu.KonsoleMenu

class WriteTagsMenu: KonsoleMenu {
    override val title: String = "Write Tags Menu"

    override val options: MutableList<String> = WriteTagsMenu.options

    override fun kallback(userInput: Int): KonsoleMenu? {
        println("kalling back yo!")
        return when (userInput) {
            1 -> readTagsMenu
            else -> null
        }
    }

    companion object {

        private val readTagsMenu: ReadTagsMenu = ReadTagsMenu()


        private val options = arrayListOf(
            "Write Single",
            "Write In Auto Poll",
            "Write In List Passive Target",
            "Configure Writing Behavior",
        )
    }

}