package jni.konsole

internal object IKonsole : IKonsoleNative() {
    init {
        try {
            System.load("C:\\Users\\ericc\\Documents\\craigen-git\\kontlin_konsole_v2\\src\\main\\resources\\kotlin_konsole.dll")
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun getProcessId(): Int =
        jGetProcessId()

    fun getProcessPriority(): Int =
        jGetProcessPriority()

    fun setProcessPriority(priority: Int, password: String = "") =
        jSetProcessPriority(priority, password)
}