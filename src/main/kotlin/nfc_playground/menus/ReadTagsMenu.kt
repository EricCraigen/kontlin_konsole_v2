package nfc_playground.menus

import kotlin_konsole.menu.KonsoleMenu

class ReadTagsMenu: KonsoleMenu {

    override val title: String = "Read Tags Menu"

    override val options: MutableList<String> = ReadTagsMenu.options

    override fun kallback(userInput: Int): KonsoleMenu? {
        println("kalling back from read tags yo!")
        return when (userInput) {
            1 -> {
                this.readSingleTag()
                null
            }
            else -> null
        }
    }

    private fun readSingleTag() {
        println("reading single tag...")
    }

    companion object {

        private val options = arrayListOf(
            "Read Single",
            "Read In Auto Poll",
            "Read In List Passive Target"
        )
    }

}