package kotlin_konsole.program

/**
 * The Konsole's status
 */
enum class KonsoleStatus {

    /**
     * The Konsole is kurrently running
     */
    RUNNING,

    /**
     * The Konsole is kurrently stopped
     */
    STOPPED,

    /**
     * The Konsole is kurrently unknown |
     * NOTE:
     */
    IN_KALLBACK;
}
