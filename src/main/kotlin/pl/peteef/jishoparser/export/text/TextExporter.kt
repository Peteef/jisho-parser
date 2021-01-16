package pl.peteef.jishoparser.export.text

import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.export.Exporting

object TextExporter : Exporting<String> {
    override fun export(data: Set<WordEntry>): String {
        val mapped = prepareSort(data)
            .mapIndexed { i, el -> TextWordEntry.fromDomain(el, i) }
            .toList()
        return serialize(TextEntries(mapped, mapped.size))
    }

    private fun prepareSort(data: Set<WordEntry>): List<WordEntry> {
        return data.sortedBy { it.reading }
    }

    private fun serialize(toBeSerialized: TextEntries): String {
        return toBeSerialized.toString()
    }
}