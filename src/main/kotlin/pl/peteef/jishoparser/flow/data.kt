package pl.peteef.jishoparser.flow

import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.processing.ProcessingType

data class SearchCriteria(
    val jlptLevel: Set<JlptLevel>
)

data class ProcessingSettings(
    val outputFilename: String,
    val processingType: ProcessingType
)