import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.data.WordEntry

val sampleEntry = WordEntry("entryId", "word", "reading", setOf("definition1", "definition2"), setOf("type1", "type2"))
val anotherEntry = WordEntry("anotherEntryId", "anotherWord", "anotherReading", setOf("definition1", "definition2"), setOf("type1", "type2"))

val noEntries = Entries(setOf())
val singleEntry = Entries(setOf(sampleEntry))
val multipleEntries = Entries(setOf(sampleEntry, anotherEntry))