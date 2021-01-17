package pl.peteef.jishoparser.processing

import pl.peteef.jishoparser.data.Entries

interface Processing {
    fun isApplicable(processingType: ProcessingType): Boolean
    fun process(data: Entries, filename: String)
}