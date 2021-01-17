package pl.peteef.jishoparser.export.json

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
    val types: Set<String>
) {
    companion object {
        fun fromDomain(responseValue: WordEntry, id: Int): JsonWordEntry {
            return JsonWordEntry(
                id,
                responseValue.reading,
                if (responseValue.word.isEmpty()) responseValue.word else responseValue.id,
                responseValue.definitions,
                responseValue.types
            )
        }
    }
}