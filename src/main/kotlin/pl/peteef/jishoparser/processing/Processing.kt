package pl.peteef.jishoparser.processing

import pl.peteef.jishoparser.data.WordEntry

interface Processing {
    fun isApplicable(processingType: ProcessingType): Boolean
    fun process(data: Set<WordEntry>, filename: String)
}