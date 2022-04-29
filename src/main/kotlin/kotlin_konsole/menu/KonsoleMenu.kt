package kotlin_konsole.menu

/**
 * A Konsole's menu |
 * Defines the concrete properties and functionality required for a menu-driven Program
 */
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
     * Resolves the users input, returns the next KonsoleMenu if menu selection resolves as a KonsoleMenu;
     * If menu selection calls a method, "do work" within that method and return null to get back out to the Konsole;
     * Will present the user with this KonsoleMenu.
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