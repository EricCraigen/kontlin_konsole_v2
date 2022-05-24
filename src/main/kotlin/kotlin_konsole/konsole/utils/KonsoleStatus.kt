package kotlin_konsole.konsole.utils

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
     * The Konsole is kurrently kalling back in a KonsoleMenu |
     */
    IN_KALLBACK,

    /**
     * The Konsole status is kurrently unknown |
     */
    UNKNOWN;
}
