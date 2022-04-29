package kotlin_konsole.menu

abstract class MainMenu: KonsoleMenu{

    /**
     * The MainMenu's title
     */
    abstract override val title: String

    /**
     * A list of the MainMenu's options
     */
    abstract override val options: MutableList<String>

    /**
     * The MainMenu's back option
     */
    override val backOption: Int
        get() = options.size + 1

}