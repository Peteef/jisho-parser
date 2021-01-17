package pl.peteef.jishoparser.cli

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.DefaultHelpFormatter

object ArgumentParser {
    private val PROLOGUE = """
        |Welcome to the Jisho Parser v0.1!
        |This is the very beginning of the project making Japanese study a little bit easier.
        |Using this simple program you can convert word entries taken from Jisho.org into a format more suitable to learn vocabulary.
        |よろしくお願いします！
    """.trimMargin()

    private val EPILOGUE = """
        |Created by Peteef, 2021.
        |https://github.com/Peteef/jisho-parser
    """.trimMargin()

    fun parseArguments(args: Array<String>): Arguments {
         return ArgParser(args, helpFormatter = DefaultHelpFormatter(PROLOGUE, EPILOGUE)).parseInto(::Arguments)
    }
}