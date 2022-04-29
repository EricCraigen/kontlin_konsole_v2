package nfc_playground

import kotlin_konsole.menu.KonsoleMenu
import kotlin_konsole.program.Program
import nfc_playground.menus.NfcPlaygroundMainMenu

class NfcPlayground: Program() {

    override val mainMenu: NfcPlaygroundMainMenu = NfcPlaygroundMainMenu()

    override var kurrentMenu: KonsoleMenu = mainMenu

    override var previousMenu: KonsoleMenu = mainMenu

}