package pl.peteef.jishoparser.processing

import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.export.Exporting
import pl.peteef.jishoparser.save.Saving

class ProcessingStrategy(
    private val type: ProcessingType,
    private val exporter: Exporting,
    private val saver: Saving
) : Processing {
    override fun isApplicable(processingType: ProcessingType): Boolean = type === processingType

    override fun process(data: Set<WordEntry>, filename: String) {
        val toBeSaved = exporter.export(data)
        saver.save(toBeSaved, filename)
    }
}