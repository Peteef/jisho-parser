package pl.peteef.jishoparser.save

import java.io.File
import java.time.LocalDateTime

class FileSaver(
    private val extension: String,
    private val filename: String = "result-${LocalDateTime.now()}"
) : Saving {
    override fun save(data: String) {
        File("$filename.$extension").writeText(data)
    }
}