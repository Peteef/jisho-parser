package pl.peteef.jishoparser.export.text

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
    val definitions: Set<String>,
    val types: Set<String>
) {
    companion object {
        fun fromDomain(responseValue: WordEntry, id: Int): TextWordEntry {
            return TextWordEntry(
                id,
                responseValue.reading,
                if (responseValue.word.isEmpty()) responseValue.word else responseValue.id,
                responseValue.definitions,
                responseValue.types
            )
        }
    }

    override fun toString(): String {
        val formatDefinitions: (Set<String>) -> String = { d -> d.joinToString(separator = "\n") { "|        - $it" } }
        return """
            |$id: $reading ($kanji)
            |     Type: ${types.joinToString()}
            |     Definitions:
            |     ${formatDefinitions(definitions)}
        """.trimMargin("|")
    }
}