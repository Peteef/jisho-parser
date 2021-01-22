package pl.peteef.jishoparser.export.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.export.Exporting

object JsonExporter : Exporting<String> {
    private val serializer: Gson = GsonBuilder().setPrettyPrinting().create()

    override fun export(data: Entries): String {
        val mapped = prepareSort(data.entries)
            .mapIndexed { i, el -> JsonWordEntry.fromDomain(el, i) }
            .toList()
        return serialize(JsonEntries(mapped, mapped.size))
    }

    private fun prepareSort(data: Set<WordEntry>): List<WordEntry> {
        return data.sortedWith(compareBy({ it.jlpt }, { it.reading }))
    }

    private fun serialize(toBeSerialized: JsonEntries): String = serializer.toJson(toBeSerialized)
}