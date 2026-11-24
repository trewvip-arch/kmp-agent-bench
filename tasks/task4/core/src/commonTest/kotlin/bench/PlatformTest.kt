package bench

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PlatformTest {
    @Test
    fun platformNameIsNotBlank() {
        assertTrue(platformName().isNotBlank())
    }

    @Test
    fun jvmTargetReportsJvm() {
        assertEquals("jvm", platformName())
    }
}
