package pl.peteef.jishoparser.save

import java.time.LocalDateTime

interface Saving {
    fun save(data: String, filename: String = "result-${LocalDateTime.now()}")
}