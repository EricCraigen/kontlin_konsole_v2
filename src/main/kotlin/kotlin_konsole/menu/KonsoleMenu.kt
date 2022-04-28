package kotlin_konsole.menu

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
     * The KonsoleMenu's back option
     */
    val backOption: Int
        get() = options.size + 1


    /**
     * Abstract kallback to execute the KonsoleMenu's business logic according to the users input
     */
    fun kallback(userInput: Int): KonsoleMenu?

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

}