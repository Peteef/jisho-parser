package pl.peteef.jishoparser.flow

import pl.peteef.jishoparser.client.HttpClient
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

    private fun fetchData(): Collection<PageEntity> {
        return httpClient.get(searchCriteria.jlptLevel)
    }

    private fun mapData(data: Collection<PageEntity>): Entries {
        return Entries.fromResponse(data)
    }

    private fun process(entries: Entries) {
        if (processor.supports(processingSettings.processingType)) {
           processor.process(entries.entries, processingSettings.processingType, processingSettings.outputFilename)
        } else {
            throw IllegalArgumentException()
        }
    }
}