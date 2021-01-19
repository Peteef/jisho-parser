package pl.peteef.jishoparser.export.spreadsheet

import pl.peteef.jishoparser.test.multipleEntries
import pl.peteef.jishoparser.test.noEntries
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.util.CellAddress
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.peteef.jishoparser.test.singleEntry

internal class SpreadsheetExporterTest {
    private val exporter = SpreadsheetExporter

    @Test
    fun itShouldExportNoEntries() {
        val result = exporter.export(noEntries)
        val sheet = result.getSheet("Vocabulary")
        assertHeader(sheet)
        assertRow(sheet, 2)
    }

    @Test
    fun itShouldExportSingleEntry() {
        val result = exporter.export(singleEntry)
        val sheet = result.getSheet("Vocabulary")
        assertHeader(sheet)
        assertRow(sheet, 2, "0", "reading", "entryId", "definition1\ndefinition2", "type1\ntype2")
    }

    @Test
    fun itShouldSortAndExportMultipleEntries() {
        val result = exporter.export(multipleEntries)
        val sheet = result.getSheet("Vocabulary")
        assertHeader(sheet)
        assertRow(sheet, 2, "0", "anotherReading", "anotherEntryId", "definition1\ndefinition2", "type1\ntype2")
        assertRow(sheet, 3, "1", "reading", "entryId", "definition1\ndefinition2", "type1\ntype2")
    }

    private fun assertHeader(sheet: Sheet) {
        assertRow(sheet, 1, "Id", "Reading", "Kanji", "Definitions", "Types")
    }

    private fun assertRow(sheet: Sheet, row: Int, id: String = "", reading: String = "", kanji: String = "", definitions: String = "", types: String = "") {
        assertCell(sheet, CellAddress("A$row"), id)
        assertCell(sheet, CellAddress("B$row"), reading)
        assertCell(sheet, CellAddress("C$row"), kanji)
        assertCell(sheet, CellAddress("D$row"), definitions)
        assertCell(sheet, CellAddress("E$row"), types)
    }

    private fun assertCell(sheet: Sheet, cellAddress: CellAddress, expectedValue: String) {
        val value = if (cellAddress.row <= sheet.lastRowNum)
            sheet.getRow(cellAddress.row)
                .getCell(cellAddress.column, RETURN_BLANK_AS_NULL)
                .stringCellValue
        else ""
        assertEquals(expectedValue,value)
    }
}