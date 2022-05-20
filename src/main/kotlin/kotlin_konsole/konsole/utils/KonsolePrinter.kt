package kotlin_konsole.konsole.utils

import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.menu.MainMenu
import java.sql.Timestamp

/**
 * The Konsole's Printer |
 * A singleton helper dedicated to printing to the Konsole.
 */
object KonsolePrinter {

    /**
     * A message to prompt the user to make a selection from the KonsoleMenu's options |
     * MAKE SURE YOU CLEAN UP WITH ANSI_RESET!
     */
    private const val USER_INPUT_PROMPT = "${KonsoleKolors.CYAN_BOLD_BRIGHT} Selection: "

    /**
     * A message to alert the user that they have made an invalid selection from the KonsoleMenu's options
     */
    private const val INVALID_SELECTION_PREFIX = "\n\nInvalid selection...\n\n"

    /**
     * Prints a KonsoleMenu as a numbered list; Automatically appends a back option to the KonsoleMenu's options |
     */
    fun printMenuOptions(menu: KonsoleMenu) {
        var retStr = "${menu.menuTitleTextColor} ${menu.title}\n\n${KonsoleKolors.RESET}"
        val menuWidth = menu.menuWidth()

        menu.options.mapIndexed { index, option ->
            val padToMenuWidth = menuWidth - option.length
            val paddedOption = option.padEnd(option.length + padToMenuWidth, ' ')
            retStr += " ${menu.optionsBackGroundColor} ${index + 1}. $paddedOption ${KonsoleKolors.RESET}\n"
        }

        val paddedBackOption: String =
            if (menu is MainMenu) {
                " ${menu.optionsBackGroundColor} ${menu.options.size + 1}. Exit ".padEnd(menuWidth + 14, ' ') + "${KonsoleKolors.RESET}\n"
            } else {
                " ${menu.optionsBackGroundColor} ${menu.options.size + 1}. Back ".padEnd(menuWidth + 14, ' ') + "${KonsoleKolors.RESET}\n"
            }

        retStr += paddedBackOption
        print(retStr, newlineBefore = true, newlineAfter = true)
    }

    /**
     * Prints a message alerting the user that the KonsoleStatus has been set to STOPPED
     */
    fun killKonsole() {
//        val retStr = "\nKilling Konsole..."
//        this.print(retStr, newlineBefore = true, textColor = KonsoleKolors.RED_BOLD_BRIGHT, newlineAfter = true)
        // todo verify this works.... need to run outside
        Runtime.getRuntime().addShutdownHook(Thread {
            val retStr = "\nKilling Konsole..."
            this.print(retStr, newlineBefore = true, textColor = KonsoleKolors.RED_BOLD_BRIGHT, newlineAfter = true)
        })
    }

    /**
     * Prints a prompt for the user to provide input to select from the KonsoleMenu's options
     */
    fun userInputPrompt() {
        this.print(USER_INPUT_PROMPT, newlineBefore = false, newlineAfter = false)
    }

    /**
     * Prints a message when the user has provided invalid input for a KonsoleMenu's options
     */
    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int?) {
        var retStr = KonsoleKolors.RED_BOLD_BRIGHT + INVALID_SELECTION_PREFIX + KonsoleKolors.GREEN_BOLD_BRIGHT

        retStr += if (userInput == null) {
            "Select a number for the menu option you would like to go to.\n\n"
        } else {
            "Selection given: $userInput\n\nPlease select an option less than or equal to ${currentMenu.options.size + 1}\n\n${KonsoleKolors.RESET}"
        }

        val outputStreamStart = prependKonsoleOutput(currentMenu, retStr, "invalid menu selection")
        val outputStreamComplete = appendKonsoleOutput(currentMenu, outputStreamStart, "invalid menu selection")

        this.print(outputStreamComplete, newlineBefore = false, newlineAfter = true)
    }

    fun prependKonsoleOutput(menu: KonsoleMenu, retStr: String, kallbackSignature: String): String {
        var startStr = "${menu.outputStreamColors[0]}| ---{ ${menu.outputStreamColors[1]}${Timestamp(System.currentTimeMillis())} ${menu.outputStreamColors[0]}}--- | ---{ ${KonsoleKolors.GREEN_BRIGHT}Output Stream Start ${menu.outputStreamColors[0]}}--- | ---{ ${menu.outputStreamColors[2]}kallbackSignature: $kallbackSignature ${menu.outputStreamColors[0]}}--- |${KonsoleKolors.RESET}"
        startStr += retStr

        return startStr
    }

    fun appendKonsoleOutput(menu: KonsoleMenu, retStr: String, kallbackSignature: String): String {
        var tempStr = retStr

        val endStr = "${menu.outputStreamColors[0]}| ---{ ${menu.outputStreamColors[1]}${Timestamp(System.currentTimeMillis())} ${menu.outputStreamColors[0]}}--- | ---{ ${KonsoleKolors.RED_BRIGHT}Output Stream End ${menu.outputStreamColors[0]}}--- | ---{ ${menu.outputStreamColors[2]}kallbackSignature: $kallbackSignature ${menu.outputStreamColors[0]}}--- |${KonsoleKolors.RESET}"
        tempStr += endStr

        return tempStr
    }

    /**
     * Prints the given string; For printing within KonsoleMenu's
     */
    fun print(str: String, textColor: String? = "", newlineBefore: Boolean, newlineAfter: Boolean) {
        if (newlineBefore) {
            if (newlineAfter) {
                println("$textColor\n$str${KonsoleKolors.RESET}")
            } else {
                print("$textColor\n$str${KonsoleKolors.RESET}")
            }
        } else {
            if (newlineAfter) {
                println("$textColor$str${KonsoleKolors.RESET}")
            } else {
                print("$textColor$str${KonsoleKolors.RESET}")
            }
        }
    }
}