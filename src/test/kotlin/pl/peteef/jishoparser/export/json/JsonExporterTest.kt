package pl.peteef.jishoparser.export.json

import pl.peteef.jishoparser.test.multipleEntries
import pl.peteef.jishoparser.test.noEntries
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pl.peteef.jishoparser.test.singleEntry

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
        val result = exporter.export(noEntries, false)
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
            |      ],
            |      "jlpt": "JLPT-N5"
            |    }
            |  ],
            |  "count": 1
            |}
        """.trimMargin()
        val result = exporter.export(singleEntry, false)
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
            |      ],
            |      "jlpt": "JLPT-N5"
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
            |      ],
            |      "jlpt": "JLPT-N5"
            |    },
            |    {
            |      "id": 2,
            |      "reading": "anotherLevelReading",
            |      "kanji": "anotherLevelEntryId",
            |      "definitions": [
            |        "definition1",
            |        "definition2"
            |      ],
            |      "types": [
            |        "type1",
            |        "type2"
            |      ],
            |      "jlpt": "JLPT-N4"
            |    }
            |  ],
            |  "count": 3
            |}
        """.trimMargin()
        val result = exporter.export(multipleEntries, false)
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun itShouldExportWithRomaji() {
        val expected = """
            |{
            |  "data": [
            |    {
            |      "id": 0,
            |      "reading": "reading",
            |      "kanji": "entryId",
            |      "romaji": "romaji",
            |      "definitions": [
            |        "definition1",
            |        "definition2"
            |      ],
            |      "types": [
            |        "type1",
            |        "type2"
            |      ],
            |      "jlpt": "JLPT-N5"
            |    }
            |  ],
            |  "count": 1
            |}
        """.trimMargin()
        val result = exporter.export(singleEntry, true)
        Assertions.assertEquals(expected, result)
    }
}