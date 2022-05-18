package jni_tester

internal object JniTesterKotlin : JniTesterJava() {
    var test = "testing..."

    init {
        try {
            System.load("C:\\Users\\ericc\\Documents\\craigen-git\\kontlin_konsole_v2\\src\\main\\resources\\jni_tester.dll")
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun add(a: Int, b: Int): Int =
        jAdd(a, b)
}