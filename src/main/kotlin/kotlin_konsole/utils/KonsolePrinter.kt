package kotlin_konsole.utils

import kotlin_konsole.menu.KonsoleMenu

/**
 * A singleton helper dedicated to printing to the Konsole. This classes functions return strings in addition to
 * printing the return object that is currently a String.
 * todo add serialization and make these return JSON
 */
object KonsolePrinter {

    /**
     * A message to prompt the user to make a selection from the KonsoleMenu's options
     */
    private const val USER_INPUT_PROMPT = "Selection: "

    /**
     * A message to alert the user they have made an invalid selection from the KonsoleMenu's options
     */
    private const val INVALID_SELECTION_PREFIX = "Invalid selection...\n\n"

    /**
     * Prints a KonsoleMenu
     */
    fun printMenuOptions(menu: KonsoleMenu): String {
        var retStr = "\n${menu.title}\n\n"
        menu.options.mapIndexed { index, option ->
            retStr += "${index + 1}. $option\n"
        }
        retStr += "${menu.options.size + 1}. Back\n"
        println(retStr)
        return retStr
    }

    /**
     * Prints the "Killing Konsole" message
     */
    fun killKonsole(): String {
        val retStr = "\nKilling Konsole...\n"
        println(retStr)
        return retStr
    }

    /**
     * Prints the user input prompt
     */
    fun userInputPrompt(): String {
        print(this.USER_INPUT_PROMPT)
        return this.USER_INPUT_PROMPT
    }

    /**
     * Prints the invalid KonsoleMenu option selection
     */
    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int): String {
        var retStr = this.INVALID_SELECTION_PREFIX
        retStr += "Selection given: $userInput\n"
        retStr += "Please selection an option less than or equal to ${currentMenu.options.size + 1}"
        println(retStr)
        return retStr
    }
}