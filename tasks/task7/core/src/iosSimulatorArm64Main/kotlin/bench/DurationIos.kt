package bench

public actual fun formatDuration(ms: Long): String {
    val safeMs = if (ms < 0L) 0L else ms
    if (safeMs < 1_000L) return "${safeMs}ms"

    if (safeMs < 60_000L) {
        // 截断到十分之一秒，不四舍五入。
        val tenths = safeMs % 60_000L / 100L
        return "${tenths / 10L}.${tenths % 10L}s"
    }

    val totalSeconds = safeMs / 1_000L
    return formatMinutesSeconds(
        minutes = totalSeconds / 60L,
        seconds = totalSeconds % 60L,
    )
}
