package pl.peteef.jishoparser.data

import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.client.PageEntity
import pl.peteef.jishoparser.client.Position
import pl.peteef.jishoparser.data.romaji.ReadingRomanizer.toRomaji

data class Entries (val entries: Set<WordEntry>) {
    companion object {
        fun fromResponse(response: Map<JlptLevel, List<PageEntity>>): Entries {
            val entries = response.entries
                .flatMap { (l, page) -> page.flatMap { it.data.map { pos -> l to pos } } }
                .map { (l, pos) -> WordEntry.fromResponse(pos, l) }
                .toSet()
            return Entries(entries)
        }
    }
}

data class WordEntry(
    val id: String,
    val word: String,
    val reading: String,
    val romaji: String,
    val definitions: Set<String>,
    val types: Set<String>,
    val jlpt: JlptLevel
) {
    companion object {
        fun fromResponse(responseValue: Position, jlptLevel: JlptLevel): WordEntry {
            val firstWord = responseValue.japanese.first()
            val firstDefinition = responseValue.senses.first()
            return WordEntry(
                responseValue.slug,
                firstWord.word.orEmpty(),
                firstWord.reading,
                toRomaji(firstWord.reading),
                firstDefinition.englishDefinitions,
                firstDefinition.partsOfSpeech,
                jlptLevel
            )
        }
    }
}