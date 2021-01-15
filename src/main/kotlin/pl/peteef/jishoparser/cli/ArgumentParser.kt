package pl.peteef.jishoparser.cli

import com.xenomachina.argparser.ArgParser

object ArgumentParser {
    fun parseArguments(args: Array<String>): Arguments = ArgParser(args).parseInto(::Arguments)
}