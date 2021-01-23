package pl.peteef.jishoparser.cli

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import pl.peteef.jishoparser.client.JlptLevel.*
import pl.peteef.jishoparser.processing.ProcessingType.*
import java.time.LocalDateTime

class Arguments(parser: ArgParser) {
    val filename by parser.storing(
        "-f", "--filename", help = "output filename (without extension) [default result-{timestamp}]"
    ).default("result-${LocalDateTime.now()}")

    val processingType by parser.mapping(
        "--json-to-file" to JSON_TO_FILE,
        "--text-to-file" to TEXT_TO_FILE,
        "--excel-to-file" to EXCEL_TO_FILE,
        help = "processing type [default --text-to-file]"
    ).default(TEXT_TO_FILE)

    private val jlpt5 by parser.mapping("--jlpt5" to N5,
        help = "JLPT N5 Level [default, if none selected]"
    ).default(null)

    private val jlpt4 by parser.mapping("--jlpt4" to N4,
        help = "JLPT N4 Level [default --jlpt5 taken, if none selected]"
    ).default(null)

    private val jlpt3 by parser.mapping("--jlpt3" to N3,
        help = "JLPT N3 Level [default --jlpt5 taken, if none selected]"
    ).default(null)

    private val jlpt2 by parser.mapping("--jlpt2" to N2,
        help = "JLPT N2 Level [default --jlpt5 taken, if none selected]"
    ).default(null)

    private val jlpt1 by parser.mapping("--jlpt1" to N1,
        help = "JLPT N1 Level [default --jlpt5 taken, if none selected]"
    ).default(null)

    private val jlptAll by parser.mapping("--jlpt-all" to setOf(N5, N4, N3, N2, N1),
        help = "All JLPT Levels (it will override single selections)"
    ).default(setOf())

    val jlptLevels = if(jlptAll.isNotEmpty()) jlptAll else setOf(jlpt5, jlpt4, jlpt3, jlpt2, jlpt1).filterNotNull().toSet().ifEmpty { setOf(N5) }
}