package nfc_playground.menus

import jni.konsole.IKonsole
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

        val outputStreamStart = KonsolePrinter.prependKonsoleOutput(this, "\n\n${this.primaryTextColor}Process Id: ${this.outputStreamSuccessTextColor}$procId\n\n", "Native: GetProcessId(GetCurrentProcess()) Start")
        val outputStreamComplete = KonsolePrinter.appendKonsoleOutput(this, outputStreamStart, "Native: GetProcessId(GetCurrentProcess()) End")
        KonsolePrinter.print(outputStreamComplete, newlineBefore = true, newlineAfter = true)
    }

    private fun processPriority() {
        val procPri = IKonsole.getProcessPriority()

        val outputStreamStart = KonsolePrinter.prependKonsoleOutput(this, "\n\n${this.primaryTextColor}Process Priority: ${this.outputStreamSuccessTextColor}$procPri\n\n", "Native: GetPriorityClass(GetCurrentProcess()) Start")
        val outputStreamComplete = KonsolePrinter.appendKonsoleOutput(this, outputStreamStart, "Native: GetPriorityClass(GetCurrentProcess()) End")
        KonsolePrinter.print(outputStreamComplete, newlineBefore = true, newlineAfter = true)
    }

    private fun setProcessPriority() {
        KonsolePrinter.print("Enter new priority (-20-19): ", textColor = this.primaryTextColor, newlineBefore = true, newlineAfter = false)
        val userInput = getUserInput()

        val currentPriority = IKonsole.getProcessPriority()

        var outputStreamStart = KonsolePrinter.prependKonsoleOutput(
            this,
            "",
            "Native: SetPriorityClass(GetCurrentProcess(), priority) Start"
        )

        IKonsole.setProcessPriority(userInput)

        val newPriority = IKonsole.getProcessPriority()

        outputStreamStart += "\n\n${this.primaryTextColor}Current Priority: ${this.outputStreamSuccessTextColor}$currentPriority\n\n${this.primaryTextColor}Requested Priority: ${this.outputStreamSuccessTextColor}$userInput\n\n${this.primaryTextColor}New Priority: ${this.outputStreamSuccessTextColor}$newPriority\n\n"

        val outputStreamComplete = KonsolePrinter.appendKonsoleOutput(
            this,
            outputStreamStart,
            "Native: SetPriorityClass(GetCurrentProcess(), priority) End",
        )
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