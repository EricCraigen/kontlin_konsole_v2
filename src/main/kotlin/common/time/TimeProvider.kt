package common.time

object TimeProvider {
    private var jvm_diff: Long = 0

    init {
        jvm_diff = System.currentTimeMillis() * 1000000 - System.nanoTime()
    }

    val accurateNow: Long
        get() = System.nanoTime() + jvm_diff
}