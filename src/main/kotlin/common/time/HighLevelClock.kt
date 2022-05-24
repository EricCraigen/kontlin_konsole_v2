package common.time

import common.time.TimeProvider.accurateNow
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class HighLevelClock(private val zone: ZoneId) : Clock() {
    override fun getZone(): ZoneId {
        return zone
    }

    override fun withZone(zoneId: ZoneId): Clock {
        return HighLevelClock(zoneId)
    }

    override fun instant(): Instant {
        val nanos = accurateNow
        return Instant.ofEpochSecond(nanos / nano_per_second, nanos % nano_per_second)
    }

    companion object {
        var nano_per_second = 1000000000L
    }
}