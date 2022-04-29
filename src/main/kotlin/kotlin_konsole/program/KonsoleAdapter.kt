package kotlin_konsole.program

import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu

/**
 * The Konsole. Defines the concrete properties and functionality required for a menu-driven Program
 */
interface KonsoleAdapter {

    /**
     * The Konsole's kurrent status
     */
    var konsoleStatus: KonsoleStatus

    /**
     * The kurrent user input
     */
    var kurrentUserInput: Int?

    /**
     * The Konsole's kurrently selected KonsoleMenu
     */
    var kurrentMenu: KonsoleMenu

    /**
     * The Konsole's previously selected KonsoleMenu
     */
    var previousMenu: KonsoleMenu

    /**
     * The Konsole's main KonsoleMenu |
     * NOTE: Every program requires AT LEAST a main KonsoleMenu
     */
    val mainMenu: MainMenu

    /**
     * Returns the "Killing Konsole..." message as a string |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun killKonsole(): String {
        return KonsolePrinter.killKonsole()
    }

    /**
     * Returns the current KonsoleMenu's options as a string |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun printMenuOptions(currentMenu: KonsoleMenu): String {
        return KonsolePrinter.printMenuOptions(currentMenu)
    }

    /**
     * Uses the KonsolePrinter to print the user selection prompt;
     * Returns a readLine() for processing userInput in Konsole kontrol logic |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun promptUserForInput(): String? {
        KonsolePrinter.userInputPrompt()
        return readLine()
    }

    /**
     * Returns a tailored per KonsoleMenu, invalid selection message as a string |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int?): String {
        return KonsolePrinter.invalidSelection(currentMenu, userInput)
    }

    /**
     * Reverts the kurrentMenu to the previousMenu
     */
    fun reverseKonsole(previousMenu: KonsoleMenu) {
        this.kurrentMenu = previousMenu
    }
}