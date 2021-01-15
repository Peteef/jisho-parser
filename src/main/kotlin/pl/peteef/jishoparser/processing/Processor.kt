package pl.peteef.jishoparser.processing

import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.export.json.JsonExporter
import pl.peteef.jishoparser.export.text.TextExporter
import pl.peteef.jishoparser.processing.ProcessingType.JSON_TO_FILE
import pl.peteef.jishoparser.processing.ProcessingType.TEXT_TO_FILE
import pl.peteef.jishoparser.save.FileSaver

object Processor {
    private val strategies: List<Processing> = listOf(
        ProcessingStrategy(JSON_TO_FILE, JsonExporter, FileSaver("json")),
        ProcessingStrategy(TEXT_TO_FILE, TextExporter, FileSaver("txt"))
    )

    fun supports(processingType: ProcessingType): Boolean = getStrategy(processingType) !== null

    fun process(data: Set<WordEntry>, processingType: ProcessingType, outputFilename: String) {
        getStrategy(processingType)?.process(data, outputFilename)
    }

    private fun getStrategy(processingType: ProcessingType): Processing? {
        return strategies.find { it.isApplicable(processingType) }
    }
}