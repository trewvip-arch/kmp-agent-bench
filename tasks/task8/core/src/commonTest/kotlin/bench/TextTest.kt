package bench

import kotlin.test.Test
import kotlin.test.assertEquals

class TextTest {
    @Test
    fun slugifyCollapsesSpaces() {
        assertEquals("hello-world", slugify("  Hello   World  "))
    }

    @Test
    fun wordCountSkipsBlank() {
        assertEquals(3, wordCount(" a  b\tc "))
    }
}
