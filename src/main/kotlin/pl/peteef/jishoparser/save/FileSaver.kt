package pl.peteef.jishoparser.save

import java.io.File

class FileSaver(
    private val extension: String,
) : Saving<String> {
    override fun save(data: String, filename: String) {
        File("$filename.$extension").writeText(data)
    }
}