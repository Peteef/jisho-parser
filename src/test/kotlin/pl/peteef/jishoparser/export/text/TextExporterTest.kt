package pl.peteef.jishoparser.export.text

import pl.peteef.jishoparser.test.multipleEntries
import pl.peteef.jishoparser.test.noEntries
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.peteef.jishoparser.test.singleEntry

internal class TextExporterTest {
    private val exporter = TextExporter

    @Test
    fun itShouldExportNoEntries() {
        val result = exporter.export(noEntries, false)
        val expected = """
            |Total: 0
            |-------------
            |
            |
        """.trimMargin()
        assertEquals(expected, result)
    }

    @Test
    fun itShouldExportSingleEntry() {
        val result = exporter.export(singleEntry, false)
        val expected = """
            |Total: 1
            |-------------
            |
            |0: reading (entryId) <N5>
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
        """.trimMargin()
        assertEquals(expected, result)
    }

    @Test
    fun itShouldSortByAndExportMultipleEntries() {
        val result = exporter.export(multipleEntries, false)
        val expected = """
            |Total: 3
            |-------------
            |
            |0: anotherReading (anotherEntryId) <N5>
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
            |
            |1: reading (entryId) <N5>
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
            |
            |2: anotherLevelReading (anotherLevelEntryId) <N4>
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
        """.trimMargin()
        assertEquals(expected, result)
    }

    @Test
    fun itShouldExportWithRomaji() {
        val result = exporter.export(singleEntry, true)
        val expected = """
            |Total: 1
            |-------------
            |
            |0: reading (entryId) [romaji] <N5>
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
        """.trimMargin()
        assertEquals(expected, result)
    }
}