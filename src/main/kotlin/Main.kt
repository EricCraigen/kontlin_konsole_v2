import kotlinx.coroutines.runBlocking

fun main() {

    try {
        runBlocking {
            val nfcPlayground: Program = NfcPlayground()

            nfcPlayground.run()
        }
    } catch (ex: IllegalStateException) {
        println(ex.message)
    }
}

class NfcPlayground(): Program() {
    override var konsoleStatus: KonsoleStatus = KonsoleStatus.UNKNOWN

}

abstract class Program(

): Konsole {

    final override val mainMenu: MainMenu = MainMenu()

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
                kurrentMenu.kurrentUserInput = userInput

                // Kall kallback on the kurrent menu to either run the menu's kallback or restart the konsole after setting the Konsole's kurrent menu to the nextMenu
                val nextMenu = kurrentMenu.kallback()

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

interface Konsole {

    /**
     * The Konsole's kurrent status
     */
    var konsoleStatus: KonsoleStatus

    /**
     * The Konsole's kurrently selected KonsoleMenu
     */
    var kurrentMenu: KonsoleMenu

    /**
     * The Konsole's previously selected KonsoleMenu
     */
    var previousMenu: KonsoleMenu

    /**
     * The Konsole's main KonsoleMenu
     */
    val mainMenu: KonsoleMenu

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
     * Returns a readline() for processing userInput in Konsole kontrol logic |
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
    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int): String {
        return KonsolePrinter.invalidSelection(currentMenu, userInput)
    }

    /**
     * Reverts the kurrentMenu to the previousMenu
     */
    fun reverseKonsole(previousMenu: KonsoleMenu) {
        this.kurrentMenu = previousMenu
    }
}

enum class KonsoleStatus(val value: UByte) {
    UNKNOWN(0x00u),
    RUNNING(0x01u),
    STOPPED(0x02u);

    companion object {
        private val map = values().associateBy(KonsoleStatus::value)

        fun fromValue(value: UByte) = map.getValue(value)
    }
}

object KonsolePrinter {
    private const val USER_INPUT_PROMPT = "Selection: "
    private const val INVALID_SELECTION_PREFIX = "Invalid selection...\n\n"

    fun printMenuOptions(menu: KonsoleMenu): String {
        var retStr = "\n${menu.title}\n\n"
        menu.options.mapIndexed { index, option ->
            retStr += "${index + 1}. $option\n"
        }
        retStr += "${menu.options.size + 1}. Back\n"
        println(retStr)
        return retStr
    }

    fun killKonsole(): String {
        val retStr = "\nKilling Konsole...\n"
        println(retStr)
        return retStr
    }

    fun userInputPrompt(): String {
        print(this.USER_INPUT_PROMPT)
        return this.USER_INPUT_PROMPT
    }

    fun invalidSelection(currentMenu: KonsoleMenu, userInput: Int): String {
        var retStr = this.INVALID_SELECTION_PREFIX
        retStr += "Selection given: $userInput\n"
        retStr += "Please selection an option less than or equal to ${currentMenu.options.size + 1}"
        println(retStr)
        return retStr
    }
}

class ReadTagsMenu: KonsoleMenu {

    override val title: String = "Read Tags Menu"

    override val options: MutableList<String> = ReadTagsMenu.options

    override var kurrentUserInput: Int? = null

    override fun kallback(): KonsoleMenu? {
        println("kalling back from read tags yo!")
        return when (this.kurrentUserInput) {
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

class MainMenu: KonsoleMenu {

    override val title: String = "NFC Playground"

    override val options: MutableList<String> = MainMenu.options

    override var kurrentUserInput: Int? = null

    override fun kallback(): KonsoleMenu? {
        println("kalling back yo!")
        return when (this.kurrentUserInput) {
            1 -> readTagsMenu
            else -> null
        }
    }

    companion object {

        private val readTagsMenu: ReadTagsMenu = ReadTagsMenu()

        private val options = arrayListOf(
            "Read Tags",
            "Write Tags",
            "Utilities",
        )
    }

}

interface KonsoleMenu {

    /**
     * The KonsoleMenu's title
     */
    val title: String

    /**
     * A list of the KonsoleMenu's options
     */
    val options: MutableList<String>

    /**
     * The kurrent user input todo can this be refactored to the Konsole itself and not break anything??
     */
    var kurrentUserInput: Int?

    /**
     * The KonsoleMenu's back option
     */
    val backOption: Int
        get() = options.size + 1


    /**
     * Abstract kallback to execute the KonsoleMenu's business logic according to the users input
     */
    fun kallback(): KonsoleMenu?

    /**
     * Determines if the users input IS the back option
     */
    fun userInputIsBackOption(userInput: Int): Boolean {
        return userInput == this.backOption
    }

    /**
     * Validates that the users input is LESS THAN the back option
     */
    fun validateUserInput(userInput: Int): Boolean {
        return userInput < this.backOption
    }

    /**
     * Reverts the KonsoleMenu's properties back to default values; Kalled after run cycle forking comes to an end
     * and the Konsole is about to be run again...
     */
    fun klean() {
        println("kleaning")
        this.kurrentUserInput = null
    }
}