package kotlin_konsole.konsole.utils

interface KonsoleMenuTheme {
    /**
     * MUST BE A KonsoleKolors KOLOR!
     */
    val primaryTextColor: String

    /**
     * MUST BE A KonsoleKolors KOLOR!
     */
    val secondaryTextColor: String?

    /**
     * MUST BE A KonsoleKolors KOLOR!
     */
    val menuTitleTextColor: String

    /**
     * MUST BE A KonsoleKolors KOLOR!
     */
    val optionsBackGroundColor: String?

    /**
     * MUST BE A KonsoleKolors KOLOR!
     */
    val outputStreamColors: MutableList<String>
}