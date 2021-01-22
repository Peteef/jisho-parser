package pl.peteef.jishoparser.export.json

import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.WordEntry

data class JsonEntries(
    val data: List<JsonWordEntry>,
    val count: Int,
)

data class JsonWordEntry(
    val id: Int,
    val reading: String,
    val kanji: String,
    val definitions: Set<String>,
    val types: Set<String>,
    val jlpt: JlptLevel
) {
    companion object {
        fun fromDomain(domain: WordEntry, id: Int): JsonWordEntry {
            return JsonWordEntry(
                id,
                domain.reading,
                if (domain.word.isEmpty()) domain.word else domain.id,
                domain.definitions,
                domain.types,
                domain.jlpt
            )
        }
    }
}