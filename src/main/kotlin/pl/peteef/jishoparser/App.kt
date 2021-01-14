package pl.peteef.jishoparser

import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.export.json.JsonExporter
import pl.peteef.jishoparser.save.FileSaver

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = HttpClient.get(JlptLevel.N5)
        val mapped = result.flatMap { it.data }
            .map { WordEntry.fromResponse(it) }
            .toSet()

        val toBeSaved = JsonExporter.export(mapped)
        FileSaver("json").save(toBeSaved)
        printResults(mapped)
    }

    private fun printResults(data: Set<WordEntry>) {
        val toBePrinted = data.sortedBy { it.reading }
            .joinToString(separator = "\n") { it.toString() }
        println(toBePrinted)
    }
}