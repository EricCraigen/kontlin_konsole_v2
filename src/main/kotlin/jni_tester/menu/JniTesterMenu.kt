package jni_tester.menu

import jni_tester.JniTesterKotlin
import kotlin_konsole.konsole.KonsolePrinter
import kotlin_konsole.menu.KonsoleMenu

class JniTesterMenu: KonsoleMenu {

    override val title: String = "JNI Tester"

    override val options: MutableList<String> = Companion.options

    override suspend fun kallback(userInput: Int): KonsoleMenu? {
        return when (userInput) {
            1 -> {
                nativeAdd()
                null
            }
            else -> null
        }
    }

    private fun nativeAdd() {
        val userInput = getNativeAddInput()

        val result: Int = JniTesterKotlin.add(userInput.first, userInput.second)
        KonsolePrinter.print("Native Add Result: $result", newlineBefore = true, newlineAfter = true)
    }

    private fun getNativeAddInput(): Pair<Int, Int> {
        KonsolePrinter.print("Enter first number to add: ", newlineBefore = false, newlineAfter = false)
        val a: Int? = readLine()?.toIntOrNull()

        KonsolePrinter.print("Enter second number to add: ", newlineBefore = false, newlineAfter = false)
        val b: Int? = readLine()?.toIntOrNull()

        return if (a != null && b != null) {
            Pair(a, b)
        } else {
            KonsolePrinter.print("One of your inputs was invalid... Retry", newlineBefore = true, newlineAfter = true)
            getNativeAddInput()
        }
    }

    companion object {

        private val options = arrayListOf(
            "Native Add"
        )
    }

}