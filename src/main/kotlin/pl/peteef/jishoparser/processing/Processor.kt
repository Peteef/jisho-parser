package pl.peteef.jishoparser.processing

import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.export.json.JsonExporter
import pl.peteef.jishoparser.export.spreadsheet.SpreadsheetExporter
import pl.peteef.jishoparser.export.text.TextExporter
import pl.peteef.jishoparser.processing.ProcessingType.*
import pl.peteef.jishoparser.save.ExcelFileSaver
import pl.peteef.jishoparser.save.FileSaver

object Processor {
    private val strategies: List<Processing> = listOf(
        ProcessingStrategy(JSON_TO_FILE, JsonExporter, FileSaver("json")),
        ProcessingStrategy(TEXT_TO_FILE, TextExporter, FileSaver("txt")),
        ProcessingStrategy(EXCEL_TO_FILE, SpreadsheetExporter, ExcelFileSaver)
    )

    fun supports(processingType: ProcessingType): Boolean = getStrategy(processingType) !== null

    fun process(data: Entries, processingType: ProcessingType, outputFilename: String) {
        getStrategy(processingType)?.process(data, outputFilename)
    }

    private fun getStrategy(processingType: ProcessingType): Processing? {
        return strategies.find { it.isApplicable(processingType) }
    }
}