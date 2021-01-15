package pl.peteef.jishoparser.cli

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import pl.peteef.jishoparser.client.JlptLevel.*
import pl.peteef.jishoparser.processing.ProcessingType.JSON_TO_FILE
import java.time.LocalDateTime

class Arguments(parser: ArgParser) {
    val filename by parser.storing(
        "-f", "--filename", help = "output filename (without extension)"
    ).default("result-${LocalDateTime.now()}")

    val processingType by parser.mapping(
        "--json-to-file" to JSON_TO_FILE,
        help = "processing type"
    ).default(JSON_TO_FILE)

    val jlptLevel by parser.mapping(
        "--jlpt5" to N5,
        "--jlpt4" to N4,
        "--jlpt3" to N3,
        "--jlpt2" to N2,
        "--jlpt1" to N1,
        help = "JLPT Levels"
    ).default(N5)
}