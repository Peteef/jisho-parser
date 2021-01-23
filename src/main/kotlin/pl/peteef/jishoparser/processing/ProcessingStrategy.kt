package pl.peteef.jishoparser.processing

import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.export.Exporting
import pl.peteef.jishoparser.save.Saving

class ProcessingStrategy<T>(
    private val type: ProcessingType,
    private val exporter: Exporting<T>,
    private val saver: Saving<T>
) : Processing {
    override fun isApplicable(processingType: ProcessingType): Boolean = type === processingType

    override fun process(data: Entries, filename: String, withRomaji: Boolean) {
        val toBeSaved = exporter.export(data, withRomaji)
        saver.save(toBeSaved, filename)
    }
}