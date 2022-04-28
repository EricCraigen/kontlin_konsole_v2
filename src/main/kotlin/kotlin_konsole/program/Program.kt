package kotlin_konsole.program

import kotlin_konsole.Konsole
import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.utils.KonsoleStatus
import nfc_playground.menus.MainMenu

abstract class Program: Konsole {

    final override val mainMenu: MainMenu = MainMenu()

    override var kurrentUserInput: Int? = null

    override var kurrentMenu: KonsoleMenu = mainMenu

    override var previousMenu: KonsoleMenu = mainMenu

    fun run() {

        // Set the program's status to running
        this.konsoleStatus = KonsoleStatus.RUNNING

        // Print the kurrentMenu
        printMenuOptions(kurrentMenu)

        // Get the user's menu selection
        val userInput: Int? = promptUserForInput()?.toIntOrNull()

        if (userInput != null) {

            // Validate userInput to be a valid menu option, given the kurrent menu
            val validatedUserInput = kurrentMenu.validateUserInput(userInput)

            if (validatedUserInput) {
                // User input IS valid

                // Set the kurrentMenu's kurrentUserInput to the validated user input
                kurrentUserInput = userInput

                // Kall kallback on the kurrent menu to either run the menu's kallback or restart the konsole after setting the Konsole's kurrent menu to the nextMenu
                val nextMenu = kurrentMenu.kallback(kurrentUserInput!!) // not null assert ok here since userInput has already been validated for null...

                if (nextMenu != null) {

                    // Set the Konsole's kurrent menu to the next menu
                    this.kurrentMenu = nextMenu

                    // "Re-run" the Konsole with the new kurrent menu set
                    run()
                } else {

                    // Set the program status to unknown since we are now handing off to the kurrent menu's kallback function to do work
                    konsoleStatus = KonsoleStatus.UNKNOWN

                    // do work in kurrent menu Class

                    // "Re-run" the Konsole
                    run()
                }
            } else {
                // user input IS NOT valid || IS the kurrent menu's back option

                val userInputIsBackOption = kurrentMenu.userInputIsBackOption(userInput)

                if (userInputIsBackOption) {
                    // User input IS the kurrent menu's back option

                    if (kurrentMenu is MainMenu) {

                        // Set the Konsole's status to stopped
                        konsoleStatus = KonsoleStatus.STOPPED

                        // Kill the Konsole
                        killKonsole()
                    } else {

                        // Revert the Konsole's kurrent menu back to the previous menu
                        reverseKonsole(previousMenu)

                        // "Re-run" the Konsole with the kurrent menu reverted
                        run()
                    }
                } else {
                    // User input IS NOT the kurrent menu's back option

                    // Alert the user they have made an invalid selection
                    invalidSelection(kurrentMenu, userInput)

                    // "Re-run" the Konsole
                    run()
                }
            }
        } else {
            // User input was not a valid Int; "Re-run" the Konsole
            run()
        }
    }
}