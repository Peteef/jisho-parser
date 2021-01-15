package pl.peteef.jishoparser

import pl.peteef.jishoparser.cli.ArgumentParser.parseArguments
import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.data.WordEntry
import pl.peteef.jishoparser.processing.Processor

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val arguments = parseArguments(args)

        val result = HttpClient.get(arguments.jlptLevel)
        val mapped = result.flatMap { it.data }
            .map { WordEntry.fromResponse(it) }
            .toSet()

        Processor.process(mapped, arguments.processingType, arguments.filename)
        printResults(mapped)
    }

    private fun printResults(data: Set<WordEntry>) {
        val toBePrinted = data.sortedBy { it.reading }
            .joinToString(separator = "\n") { it.toString() }
        println(toBePrinted)
    }
}