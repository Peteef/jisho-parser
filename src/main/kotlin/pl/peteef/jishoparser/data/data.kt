package pl.peteef.jishoparser.data

import pl.peteef.jishoparser.client.Position

data class WordEntry(
    val id: String,
    val word: String,
    val reading: String,
    val definitions: Set<String>,
    val types: Set<String>
) {
    companion object {
        fun fromResponse(responseValue: Position): WordEntry {
            val firstWord = responseValue.japanese.first()
            val firstDefinition = responseValue.senses.first()
            return WordEntry(
                responseValue.slug,
                firstWord.word.orEmpty(),
                firstWord.reading,
                firstDefinition.englishDefinitions,
                firstDefinition.partsOfSpeech
            )
        }
    }
}