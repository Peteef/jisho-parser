package pl.peteef.jishoparser.export.spreadsheet

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.export.Exporting


object SpreadsheetExporter : Exporting<Workbook> {
    private const val START_ROW = 0
    private const val START_COLUMN = 0

    override fun export(data: Entries, withRomaji: Boolean): Workbook {
        val workbook: Workbook = XSSFWorkbook()

        val mappedPerLevel = prepareData(data)
        mappedPerLevel.data.forEach {
            createSheet(workbook, it)
        }

        return workbook
    }

    private fun prepareData(data: Entries): SpreadsheetEntries {
        val entries = data.entries
            .groupBy { it.jlpt }
            .map { withLevel(it) }
            .toMap()
            .toSortedMap()
        return SpreadsheetEntries(entries)
    }

    private fun withLevel(preMapped: Map.Entry<JlptLevel, List<WordEntry>>): Pair<JlptLevel, List<SpreadsheetWordEntry>> {
        return preMapped.key to preMapped.value.sortedBy { it.reading }
            .mapIndexed { i, el -> SpreadsheetWordEntry.fromDomain(el, i) }
    }

    private fun createSheet(workbook: Workbook, entriesPerLevel: Map.Entry<JlptLevel, List<SpreadsheetWordEntry>>) {
        val sheet: Sheet = workbook.createSheet("${entriesPerLevel.key} Vocabulary")
        val styles = SpreadsheetStyles(workbook)
        formatCells(sheet)
        createHeader(sheet, styles)
        insertData(entriesPerLevel.value, sheet, styles)
    }

    private fun formatCells(sheet: Sheet) {
        sheet.setColumnWidth(START_COLUMN, 3000)
        sheet.setColumnWidth(START_COLUMN + 1, 6000)
        sheet.setColumnWidth(START_COLUMN + 2, 6000)
        sheet.setColumnWidth(START_COLUMN + 3, 15000)
        sheet.setColumnWidth(START_COLUMN + 4, 15000)
    }

    private fun createHeader(sheet: Sheet, styles: SpreadsheetStyles) {
        val header: Row = sheet.createRow(START_ROW)

        val idCell = header.createCell(START_COLUMN)
        idCell.cellStyle = styles.headerStyle
        idCell.setCellValue("Id")

        val readingCell = header.createCell(START_COLUMN + 1)
        readingCell.setCellValue("Reading")
        readingCell.cellStyle = styles.headerStyle

        val kanjiCell = header.createCell(START_COLUMN + 2)
        kanjiCell.setCellValue("Kanji")
        kanjiCell.cellStyle = styles.headerStyle

        val definitionsCell = header.createCell(START_COLUMN + 3)
        definitionsCell.setCellValue("Definitions")
        definitionsCell.cellStyle = styles.headerStyle

        val typesCell = header.createCell(START_COLUMN + 4)
        typesCell.setCellValue("Types")
        typesCell.cellStyle = styles.headerStyle
    }

    private fun insertData(entries: List<SpreadsheetWordEntry>, sheet: Sheet, styles: SpreadsheetStyles) {
        entries.forEach {
            e -> createDataRow(e, sheet, styles)
        }
    }

    private fun createDataRow(entry: SpreadsheetWordEntry, sheet: Sheet, styles: SpreadsheetStyles) {
        val entryRow: Row = sheet.createRow(START_ROW + 1 + entry.id)

        val idCell = entryRow.createCell(START_COLUMN)
        idCell.cellStyle = styles.wordStyle
        idCell.setCellValue(entry.id.toString())

        val readingCell = entryRow.createCell(START_COLUMN + 1)
        readingCell.setCellValue(entry.reading)
        readingCell.cellStyle = styles.readingStyle

        val kanjiCell = entryRow.createCell(START_COLUMN + 2)
        kanjiCell.setCellValue(entry.kanji)
        kanjiCell.cellStyle = styles.wordStyle

        val definitionsCell = entryRow.createCell(START_COLUMN + 3)
        definitionsCell.setCellValue(formatBullets(entry.definitions))
        definitionsCell.cellStyle = styles.bulletStyle

        val typesCell = entryRow.createCell(START_COLUMN + 4)
        typesCell.setCellValue(formatBullets(entry.types))
        typesCell.cellStyle = styles.bulletStyle
    }

    private fun formatBullets(elements: Set<String>): String {
        return elements.joinToString(separator = "\n")
    }
}