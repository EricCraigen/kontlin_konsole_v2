package kotlin_konsole.menu

import kotlin_konsole.konsole.utils.KonsoleKolors
import kotlin_konsole.konsole.utils.KonsoleMenuTheme

/**
 * A Konsole's menu |
 * Defines the concrete properties and functionality required for a menu-driven Program
 */
interface KonsoleMenu: KonsoleMenuTheme {

    /**
     * The KonsoleMenu's title
     */
    val title: String

    /**
     * A list of the KonsoleMenu's options
     */
    val options: MutableList<String>

    val menuWidth: Int

    override val primaryTextColor: String
        get() = KonsoleKolors.BLUE_BRIGHT

    override val menuTitleTextColor: String
        get() = KonsoleKolors.CYAN_BOLD_BRIGHT

    override val secondaryTextColor: String?
        get() = KonsoleKolors.GREEN_BRIGHT

    override val optionsBackGroundColor: String?
        get() = KonsoleKolors.BLACK_BACKGROUND_BRIGHT

    override val outputStreamColors: MutableList<String>
        get() = mutableListOf(
            KonsoleKolors.BLUE_BOLD_BRIGHT, // Output Stream Bar Color
            KonsoleKolors.CYAN_BOLD_BRIGHT, // Output Stream TimeStamp
            KonsoleKolors.PURPLE_BOLD_BRIGHT, // Output Stream kallbackSignature
            KonsoleKolors.YELLOW_BOLD_BRIGHT, // Output Stream Warning Text
            KonsoleKolors.RED_BOLD_BRIGHT, // Output Stream Error Text
            KonsoleKolors.GREEN_BOLD_BRIGHT, // Output Stream Success Message
        )

    /**
     * The KonsoleMenu's back option
     */
    val backOption: Int
        get() = options.size + 1

    fun menuWidth(): Int {
        var longest = 0
        this.options.map { option ->
            if (option.length >= longest) {
                longest = option.length
            }
        }
        return longest
    }


    /**
     * Resolves the users input, returns the next KonsoleMenu if menu selection resolves as a KonsoleMenu;
     * If menu selection calls a method, "do work" within that method and return null to get back out to the Konsole;
     * Will present the user with this KonsoleMenu.
     */
    suspend fun kallback(userInput: Int): KonsoleMenu?

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