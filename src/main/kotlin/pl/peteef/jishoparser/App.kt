package pl.peteef.jishoparser

import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.client.JlptLevel
import pl.peteef.jishoparser.data.WordEntry

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = HttpClient.get(JlptLevel.N5)
        val toBePrinted = result.flatMap { it.data }
            .map { WordEntry.fromResponse(it) }
            .sortedBy { it.reading }
            .joinToString(separator = "\n") { it.toString() }
        println(toBePrinted)
    }
}