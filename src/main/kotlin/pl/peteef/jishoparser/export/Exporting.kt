package pl.peteef.jishoparser.export

import pl.peteef.jishoparser.data.WordEntry

interface Exporting {
    fun export(data: Set<WordEntry>): String
}