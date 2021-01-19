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
        val result = exporter.export(noEntries)
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
        val result = exporter.export(singleEntry)
        val expected = """
            |Total: 1
            |-------------
            |
            |0: reading (entryId)
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
        """.trimMargin()
        assertEquals(expected, result)
    }

    @Test
    fun itShouldSortAndExportMultipleEntries() {
        val result = exporter.export(multipleEntries)
        val expected = """
            |Total: 2
            |-------------
            |
            |0: anotherReading (anotherEntryId)
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
            |
            |1: reading (entryId)
            |     Type: type1, type2
            |     Definitions:
            |        - definition1
            |        - definition2
        """.trimMargin()
        assertEquals(expected, result)
    }
}