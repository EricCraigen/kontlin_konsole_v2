import kotlin_konsole.konsole.Konsole
import kotlinx.coroutines.runBlocking
import nfc_playground.NfcPlayground

fun main() {

    try {
        runBlocking {
            val nfcPlayground: Konsole = NfcPlayground()

            nfcPlayground.run()
        }
    } catch (ex: IllegalStateException) {
        println(ex.message)
    }
}








