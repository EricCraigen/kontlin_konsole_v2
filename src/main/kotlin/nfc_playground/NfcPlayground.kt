package nfc_playground

import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.konsole.Konsole
import nfc_playground.menus.NfcPlaygroundMainMenu

class NfcPlayground: Konsole() {

    // Region Abstract Properties

    override val mainMenu: NfcPlaygroundMainMenu = NfcPlaygroundMainMenu()

    override var kurrentMenu: KonsoleMenu = mainMenu

    override var previousMenu: KonsoleMenu = mainMenu

    // End Region

}