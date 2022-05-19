package jni.konsole.menu

import jni.konsole.IKonsole
import kotlin_konsole.konsole.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu

class UtilitiesMenu: KonsoleMenu {

    override val title: String = "Utilities"

    override val options: MutableList<String> = Companion.options

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

        KonsolePrinter.print("Process Id: $procId", newlineBefore = true, newlineAfter = true)
    }

    private fun processPriority() {
        val procPri = IKonsole.getProcessPriority()

        KonsolePrinter.print("Process Id: $procPri", newlineBefore = true, newlineAfter = true)
    }

    private fun setProcessPriority() {
        val userInput = getUserInput()

        IKonsole.setProcessPriority(userInput)

        KonsolePrinter.print("Priority Changed...", newlineBefore = true, newlineAfter = true)
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