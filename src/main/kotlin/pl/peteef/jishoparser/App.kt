package pl.peteef.jishoparser

import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.processing.ProcessingType.JSON_TO_FILE
import pl.peteef.jishoparser.processing.Processor

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = HttpClient.get(JlptLevel.N5)
        val mapped = result.flatMap { it.data }
            .map { WordEntry.fromResponse(it) }
            .toSet()

        Processor.process(mapped, JSON_TO_FILE)
        printResults(mapped)
    }

    private fun printResults(data: Set<WordEntry>) {
        val toBePrinted = data.sortedBy { it.reading }
            .joinToString(separator = "\n") { it.toString() }
        println(toBePrinted)
    }
}