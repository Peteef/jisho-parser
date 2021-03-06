package pl.peteef.jishoparser.export.text

import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.WordEntry

data class TextEntries(
    val data: List<TextWordEntry>,
    val count: Int,
) {
    override fun toString(): String {
        val formattedData = data.joinToString(separator = "\n\n") { it.toString() }
        return """
            |Total: $count
            |-------------
            |
            |$formattedData
        """.trimMargin("|")
    }
}

data class TextWordEntry(
    val id: Int,
    val reading: String,
    val kanji: String,
    val romaji: String?,
    val definitions: Set<String>,
    val types: Set<String>,
    val jlpt: JlptLevel
) {
    companion object {
        fun fromDomain(domain: WordEntry, id: Int, withRomaji: Boolean): TextWordEntry {
            return TextWordEntry(
                id,
                domain.reading,
                if (domain.word.isEmpty()) domain.word else domain.id,
                if (withRomaji) domain.romaji else null,
                domain.definitions,
                domain.types,
                domain.jlpt
            )
        }
    }

    override fun toString(): String {
        val formatDefinitions: (Set<String>) -> String = { d -> d.joinToString(separator = "\n") { "|        - $it" } }
        val formattedRomaji = if (romaji.isNullOrBlank()) "" else " [$romaji]"
        return """
            |$id: $reading ($kanji)${formattedRomaji} <$jlpt>
            |     Type: ${types.joinToString()}
            |     Definitions:
            |     ${formatDefinitions(definitions)}
        """.trimMargin("|")
    }
}