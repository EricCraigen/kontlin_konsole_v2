package kotlin_konsole.program

import kotlin_konsole.menu.MainMenu

/**
 * The Program; An engine for simple menu-driven program's. |
 * Defines the concrete properties and functionality required for a menu-driven Program |
 * Holds the kurrent KonsoleStatus and kurrent user input value; Defines the Program's run loop.
 */
abstract class Program: KonsoleAdapter {

    override var konsoleStatus: KonsoleStatus = KonsoleStatus.IN_KALLBACK

    override var kurrentUserInput: Int? = null


    /**
     * The Program's run loop. |
     * The logic provides the engine for kontrolling a Program's "clock cycle"
     */
    fun run() {

        // Set the program's status to running
        this.konsoleStatus = KonsoleStatus.RUNNING

        while (this.konsoleStatus != KonsoleStatus.STOPPED) {

            // Print the kurrentMenu
            printMenuOptions(this.kurrentMenu)

            // Get the user's menu selection
            val userInput: Int? = promptUserForInput()?.toIntOrNull()
            println()

            if (userInput != null) {

                // Validate userInput to be a valid menu option, given the kurrent menu
                val validatedUserInput = this.kurrentMenu.validateUserInput(userInput)

                if (validatedUserInput) {
                    // User input IS valid

                    // Set the kurrentMenu's kurrentUserInput to the validated user input
                    this.kurrentUserInput = userInput

                    // Kall kallback on the kurrent menu to either run the menu's kallback or restart the konsole after setting the Konsole's kurrent menu to the nextMenu
                    val nextMenu =
                        this.kurrentMenu.kallback(kurrentUserInput!!) // not null assert ok here since userInput has already been validated for null...

                    if (nextMenu != null) {

                        // Set the Konsole's kurrent menu to the next menu
                        this.kurrentMenu = nextMenu
                    } else {

                        // Set the program status to unknown since we are now handing off to the kurrent menu's kallback function to do work
                        this.konsoleStatus = KonsoleStatus.IN_KALLBACK

                        // do work in kurrent menu Class
                    }
                } else {
                    // user input IS NOT valid || IS the kurrent menu's back option

                    val userInputIsBackOption = this.kurrentMenu.userInputIsBackOption(userInput)

                    if (userInputIsBackOption) {
                        // User input IS the kurrent menu's back option

                        if (this.kurrentMenu is MainMenu) {

                            // Set the Konsole's status to stopped
                            this.konsoleStatus = KonsoleStatus.STOPPED

                            // Kill the Konsole
                            killKonsole()
                        } else {

                            // Revert the Konsole's kurrent menu back to the previous menu
                            reverseKonsole(this.previousMenu)
                        }
                    } else {
                        // User input IS NOT the kurrent menu's back option

                        // Alert the user they have made an invalid selection
                        invalidSelection(this.kurrentMenu, userInput)
                    }
                }
            } else {
                // Alert the user they have made an invalid selection
                invalidSelection(this.kurrentMenu, userInput)
            }
        }
    }
}