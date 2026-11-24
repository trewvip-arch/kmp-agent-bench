package bench

/**
 * 把毫秒数格式化成易读的时长：
 * - 不足 1 秒显示毫秒，如 `850ms`
 * - 不足 1 分钟显示秒并保留一位小数，如 `12.3s`
 * - 1 分钟及以上显示分和补两位的秒，如 `1m05s`（毫秒部分忽略）
 * - 负数按 0 处理
 */
fun formatDuration(ms: Long): String {
    val millis = ms.coerceAtLeast(0L)
    return when {
        millis < 1_000L -> "${millis}ms"
        millis < 60_000L -> {
            // 以 0.1 秒为单位四舍五入，避免浮点格式化在 common 层的平台差异。
            val tenths = (millis + 50L) / 100L
            "${tenths / 10L}.${tenths % 10L}s"
        }
        else -> {
            val totalSeconds = millis / 1_000L
            val minutes = totalSeconds / 60L
            val seconds = totalSeconds % 60L
            "${minutes}m${seconds.toString().padStart(2, '0')}s"
        }
    }
}
