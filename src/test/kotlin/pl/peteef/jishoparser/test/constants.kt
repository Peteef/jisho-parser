package pl.peteef.jishoparser.test

import pl.peteef.jishoparser.client.*
import pl.peteef.jishoparser.client.JlptLevel.N4
import pl.peteef.jishoparser.client.JlptLevel.N5
import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.data.WordEntry

val japaneseWord = JapaneseWord("word", "reading")
val definition = Definition(setOf("definition1", "definition2"), setOf("type1", "type2"))
val position = Position("entryId", listOf(japaneseWord), listOf(definition))
val pageEntity = PageEntity(setOf(position))
val response = mapOf(N5 to listOf(pageEntity))

val sampleEntry = WordEntry("entryId", "word", "reading", setOf("definition1", "definition2"), setOf("type1", "type2"), N5)
val anotherEntry = WordEntry("anotherEntryId", "anotherWord", "anotherReading", setOf("definition1", "definition2"), setOf("type1", "type2"), N5)
val anotherLevelEntry = WordEntry("anotherLevelEntryId", "anotherLevelWord", "anotherLevelReading", setOf("definition1", "definition2"), setOf("type1", "type2"), N4)

val noEntries = Entries(setOf())
val singleEntry = Entries(setOf(sampleEntry))
val multipleEntries = Entries(setOf(sampleEntry, anotherEntry, anotherLevelEntry))