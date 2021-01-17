package pl.peteef.jishoparser.export

import pl.peteef.jishoparser.data.Entries

interface Exporting<T> {
    fun export(data: Entries): T
}