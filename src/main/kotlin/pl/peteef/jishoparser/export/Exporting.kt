package pl.peteef.jishoparser.export

import pl.peteef.jishoparser.data.WordEntry

interface Exporting<T> {
    fun export(data: Set<WordEntry>): T
}