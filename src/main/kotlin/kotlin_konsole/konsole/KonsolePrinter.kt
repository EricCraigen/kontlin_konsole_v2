package kotlin_konsole.konsole

import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu

/**
 * The Konsole's Printer |
 * A singleton helper dedicated to printing to the Konsole. This classes methods return strings in addition to
 * printing it's respective returned String prior to returning. |
 * todo add serialization and make these return a JSON DTO for use on a menu-driven front end application???
 * todo refactor all println()'s to utilize KonsolePrinter.print()
 */
object KonsolePrinter {

    /**
     * A message to prompt the user to make a selection from the KonsoleMenu's options
     */
    private const val USER_INPUT_PROMPT = "Selection: "

    /**
     * A message to alert the user that they have made an invalid selection from the KonsoleMenu's options
     */
    private const val INVALID_SELECTION_PREFIX = "Invalid selection...\n\n"

    /**
     * Prints a KonsoleMenu as a numbered list; Automatically appends a back option to the KonsoleMenu's options |
     */
    fun printMenuOptions(menu: KonsoleMenu): String {
        var retStr = "\n${menu.title}\n\n"
        menu.options.mapIndexed { index, option ->
            retStr += "${index + 1}. $option\n"
        }
        retStr += "${menu.options.size + 1}. ${ if (menu is MainMenu) "Exit" else "Back" }\n"
        println(retStr)
        return retStr
    }

    /**
     * Prints a message alerting the user that the KonsoleStatus has been set to STOPPED
     */
    fun killKonsole(): String {
        val retStr = "\nKilling Konsole...\n"
        println(retStr)
        return retStr
    }

    /**
     * Prints a prompt for the user to provide input to select from the KonsoleMenu's options
     */
    fun userInputPrompt(): String {
        print(USER_INPUT_PROMPT)
        return USER_INPUT_PROMPT
    }

    /**
     * Prints a message when the user has provided invalid input for a KonsoleMenu's options
     */
    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int?): String {
        var retStr = INVALID_SELECTION_PREFIX
        retStr += if (userInput == null) {
            "Please select a number for the menu option you would like to go to."
        } else {
            "Selection given: $userInput\n\nPlease select an option less than or equal to ${currentMenu.options.size + 1}"
        }
        println(retStr)
        return retStr
    }

    fun print(str: String, newlineBefore: Boolean, newlineAfter: Boolean) {
        if (newlineBefore) {
            if (newlineAfter) {
                println("\n$str")
            } else {
                print("\n$str")
            }
        } else {
            if (newlineAfter) {
                println(str)
            } else {
                print(str)
            }
        }
    }
}