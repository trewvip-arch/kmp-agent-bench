package bench

/**
 * 把毫秒数格式化成人类可读的时长字符串。
 *
 * - 负数按 0 处理，返回 `"0ms"`；
 * - 不足 1 秒时返回毫秒，如 `"850ms"`；
 * - 不足 1 分钟时返回保留一位小数的秒数，如 `"12.3s"`；
 * - 达到 1 分钟时返回 `"XmYYs"`，秒数补齐两位，如 `"1m05s"`。
 */
fun formatDuration(ms: Long): String {
    if (ms < 0) return "0ms"
    if (ms < 1_000) return "${ms}ms"
    if (ms < 60_000) {
        val seconds = ms / 1_000
        val tenths = (ms % 1_000) / 100
        return "$seconds.${tenths}s"
    }

    val minutes = ms / 60_000
    val seconds = (ms % 60_000) / 1_000
    return "${minutes}m${seconds.toString().padStart(2, '0')}s"
}
