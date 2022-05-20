package nfc_playground.menus

import jni.konsole.IKonsole
import kotlin_konsole.konsole.utils.KonsoleKolors
import kotlin_konsole.konsole.utils.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu

class UtilitiesMenu: KonsoleMenu {

    override val title: String = "Utilities"

    override val options: MutableList<String> = Companion.options

    override val menuWidth: Int = this.menuWidth()

    override suspend fun kallback(userInput: Int): KonsoleMenu? {
        return when (userInput) {
            1 -> {
                processId()
                null
            }
            2 -> {
                processPriority()
                null
            }
            3 -> {
                setProcessPriority()
                null
            }
            else -> null
        }
    }

    private fun processId() {
        val procId = IKonsole.getProcessId()

        val outputStreamStart = KonsolePrinter.prependKonsoleOutput(this, "\n\n${this.outputStreamColors[5]}Process Id: ${this.outputStreamColors[3]}$procId\n\n", "Native: GetProcessId(GetCurrentProcess()) Start")
        val outputStreamComplete = KonsolePrinter.appendKonsoleOutput(this, outputStreamStart, "Native: GetProcessId(GetCurrentProcess()) End")
        KonsolePrinter.print(outputStreamComplete, newlineBefore = true, newlineAfter = true)
    }

    private fun processPriority() {
        val procPri = IKonsole.getProcessPriority()

        val outputStreamStart = KonsolePrinter.prependKonsoleOutput(this, "\n\n${this.outputStreamColors[5]}Process Priority: ${this.outputStreamColors[3]}$procPri\n\n", "Native: GetPriorityClass(GetCurrentProcess()) Start")
        val outputStreamComplete = KonsolePrinter.appendKonsoleOutput(this, outputStreamStart, "Native: GetPriorityClass(GetCurrentProcess()) End")
        KonsolePrinter.print(outputStreamComplete, newlineBefore = true, newlineAfter = true)
    }

    private fun setProcessPriority() {
        KonsolePrinter.print("Enter new priority (-20-19): ", textColor = this.outputStreamColors[1], newlineBefore = true, newlineAfter = false)
        val userInput = getUserInput()

        val currentPriority = IKonsole.getProcessPriority()

        IKonsole.setProcessPriority(userInput)

        val newPriority = IKonsole.getProcessPriority()

        val outputStreamStart = KonsolePrinter.prependKonsoleOutput(this, "\n\n${this.outputStreamColors[5]}Current Priority: ${this.outputStreamColors[3]}$currentPriority\n\n${this.outputStreamColors[5]}Requested Priority: ${this.outputStreamColors[3]}$userInput\n\n${this.outputStreamColors[5]}New Priority: ${this.outputStreamColors[3]}$newPriority\n\n", "Native: SetPriorityClass(GetCurrentProcess(), priority) Start")
        val outputStreamComplete = KonsolePrinter.appendKonsoleOutput(this, outputStreamStart, "Native: SetPriorityClass(GetCurrentProcess(), priority) End")
        KonsolePrinter.print(outputStreamComplete, newlineBefore = true, newlineAfter = true)
    }

    private fun getUserInput(): Int {
        return readLine()?.toIntOrNull() ?: getUserInput()
    }

    companion object {

        private val options = arrayListOf(
            "Process Id",
            "Process Priority",
            "Set Process Priority"
        )
    }

}