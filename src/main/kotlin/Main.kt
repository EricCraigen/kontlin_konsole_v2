import kotlin_konsole.program.Program
import kotlinx.coroutines.runBlocking
import nfc_playground.NfcPlayground

fun main() {

    try {
        runBlocking {
            val nfcPlayground: Program = NfcPlayground()

            nfcPlayground.run()
        }
    } catch (ex: IllegalStateException) {
        println(ex.message)
    }
}








