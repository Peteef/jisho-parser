package pl.peteef.jishoparser.test

import pl.peteef.jishoparser.client.Definition
import pl.peteef.jishoparser.client.JapaneseWord
import pl.peteef.jishoparser.client.PageEntity
import pl.peteef.jishoparser.client.Position
import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.data.WordEntry

val japaneseWord = JapaneseWord("word", "reading")
val definition = Definition(setOf("definition1", "definition2"), setOf("type1", "type2"))
val position = Position("entryId", listOf(japaneseWord), listOf(definition))
val pageEntity = PageEntity(setOf(position))

val sampleEntry = WordEntry("entryId", "word", "reading", setOf("definition1", "definition2"), setOf("type1", "type2"))
val anotherEntry = WordEntry("anotherEntryId", "anotherWord", "anotherReading", setOf("definition1", "definition2"), setOf("type1", "type2"))

val noEntries = Entries(setOf())
val singleEntry = Entries(setOf(sampleEntry))
val multipleEntries = Entries(setOf(sampleEntry, anotherEntry))