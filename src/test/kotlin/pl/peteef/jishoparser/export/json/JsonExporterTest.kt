package pl.peteef.jishoparser.export.json

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.test.*

internal class JsonExporterTest {
    private val exporter = JsonExporter

    @Test
    fun itShouldExportNoEntries() {
        val expected = """
            |{
            |  "data": [],
            |  "count": 0
            |}
        """.trimMargin()
        val result = exporter.export(noEntries)
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun itShouldExportSingleEntry() {
        val expected = """
            |{
            |  "data": [
            |    {
            |      "id": 0,
            |      "reading": "reading",
            |      "kanji": "entryId",
            |      "definitions": [
            |        "definition1",
            |        "definition2"
            |      ],
            |      "types": [
            |        "type1",
            |        "type2"
            |      ]
            |    }
            |  ],
            |  "count": 1
            |}
        """.trimMargin()
        val result = exporter.export(singleEntry)
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun itShouldSortAndExportMultipleEntries() {
        val expected = """
            |{
            |  "data": [
            |    {
            |      "id": 0,
            |      "reading": "anotherReading",
            |      "kanji": "anotherEntryId",
            |      "definitions": [
            |        "definition1",
            |        "definition2"
            |      ],
            |      "types": [
            |        "type1",
            |        "type2"
            |      ]
            |    },
            |    {
            |      "id": 1,
            |      "reading": "reading",
            |      "kanji": "entryId",
            |      "definitions": [
            |        "definition1",
            |        "definition2"
            |      ],
            |      "types": [
            |        "type1",
            |        "type2"
            |      ]
            |    }
            |  ],
            |  "count": 2
            |}
        """.trimMargin()
        val result = exporter.export(Entries(setOf(sampleEntry, anotherEntry)))
        Assertions.assertEquals(expected, result)
    }
}