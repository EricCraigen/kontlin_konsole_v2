package kotlin_konsole.konsole

import kotlin_konsole.konsole.utils.KonsolePrinter
import kotlin_konsole.konsole.utils.KonsoleStatus
import kotlin_konsole.konsole.utils.KonsoleMenuTheme
import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu

/**
 * The Konsole. Defines the concrete properties and functionality required for a menu-driven Konsole program
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
     * The Konsole's kurrently selected KonsoleMenu |
     * *** Set this to your Program's MainMenu class! ***
     */
    var kurrentMenu: KonsoleMenu

    /**
     * The Konsole's previously selected KonsoleMenu
     * *** Set this to your Program's MainMenu class! ***
     */
    var previousMenu: KonsoleMenu

    /**
     * The Konsole's main KonsoleMenu |
     * NOTE: Every program requires AT LEAST a main KonsoleMenu
     */
    val mainMenu: MainMenu

//    val mainKonsoleMenuTheme: MainKonsoleMenuTheme

    /**
     * Returns the "Killing Konsole..." message as a string |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun killKonsole() {
        this.konsoleStatus = KonsoleStatus.STOPPED
        KonsolePrinter.killKonsole()
    }

    /**
     * Returns the current KonsoleMenu's options as a string |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun printMenuOptions(currentMenu: KonsoleMenu){
        KonsolePrinter.printMenuOptions(currentMenu)
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
     * Returns a tailored per KonsoleMenu invalid selection message as a string |
     * NOTE: Printing to the Konsole is done from within KonsolePrinter
     */
    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int?) {
        KonsolePrinter.invalidSelection(currentMenu, userInput)
    }

    /**
     * Reverts the kurrentMenu to the previousMenu
     */
    fun reverseKonsole(previousMenu: KonsoleMenu) {
        this.kurrentMenu = previousMenu
    }
}