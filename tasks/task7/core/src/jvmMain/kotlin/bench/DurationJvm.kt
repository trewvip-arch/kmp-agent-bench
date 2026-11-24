package bench

import java.time.Duration

public actual fun formatDuration(ms: Long): String {
    val safeMs = if (ms < 0L) 0L else ms
    if (safeMs < 1_000L) return "${safeMs}ms"

    val duration = Duration.ofMillis(safeMs)
    if (safeMs < 60_000L) {
        // 截断到十分之一秒，不四舍五入。
        val tenths = duration.toMillis() % 60_000L / 100L
        return "${tenths / 10L}.${tenths % 10L}s"
    }

    return formatMinutesSeconds(
        minutes = duration.toMinutes(),
        seconds = duration.toSeconds() % 60L,
    )
}
