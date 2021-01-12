package pl.peteef.jishoparser

import pl.peteef.jishoparser.client.HttpClient
import pl.peteef.jishoparser.client.JlptLevel

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val result = HttpClient.get(JlptLevel.N5)
        val toBePrinted = result.flatMap { it.data }
            .joinToString(separator = "\n") { it.toString() }
        println(toBePrinted)
    }
}