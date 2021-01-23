package pl.peteef.jishoparser.flow

import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.client.PageEntity
import pl.peteef.jishoparser.data.Entries
import pl.peteef.jishoparser.processing.Processor

class Job internal constructor(
    private val searchCriteria: SearchCriteria,
    private val processingSettings: ProcessingSettings
) {
    private val httpClient = HttpClient
    private val processor = Processor

    fun perform() {
        val data = fetchData()
        val mapped = mapData(data)
        process(mapped)
    }

    private fun fetchData(): Map<JlptLevel, List<PageEntity>> {
        return httpClient.get(searchCriteria.jlptLevel)
    }

    private fun mapData(data: Map<JlptLevel, List<PageEntity>>): Entries {
        return Entries.fromResponse(data)
    }

    private fun process(entries: Entries) {
        if (processor.supports(processingSettings.processingType)) {
           processor.process(entries, processingSettings)
        } else {
            throw IllegalArgumentException()
        }
    }
}