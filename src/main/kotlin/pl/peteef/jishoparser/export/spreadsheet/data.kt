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
    val definitions: Set<String>,
    val types: Set<String>
) {
    companion object {
        fun fromDomain(responseValue: WordEntry, id: Int): SpreadsheetWordEntry {
            return SpreadsheetWordEntry(
                id,
                responseValue.reading,
                if (responseValue.word.isEmpty()) responseValue.word else responseValue.id,
                responseValue.definitions,
                responseValue.types
            )
        }
    }
}