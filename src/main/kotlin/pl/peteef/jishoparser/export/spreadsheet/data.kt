package pl.peteef.jishoparser.export.spreadsheet

import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.WordEntry

data class SpreadsheetEntries(
    val data: Map<JlptLevel, List<SpreadsheetWordEntry>>
)

data class SpreadsheetWordEntry(
    val id: Int,
    val reading: String,
    val kanji: String,
    val romaji: String,
    val definitions: Set<String>,
    val types: Set<String>
) {
    companion object {
        fun fromDomain(domain: WordEntry, id: Int): SpreadsheetWordEntry {
            return SpreadsheetWordEntry(
                id,
                domain.reading,
                if (domain.word.isEmpty()) domain.word else domain.id,
                domain.romaji,
                domain.definitions,
                domain.types
            )
        }
    }
}