package pl.peteef.jishoparser.data.romaji

import com.moji4j.MojiConverter

object ReadingRomanizer {
    private val converter = MojiConverter()

    fun toRomaji(kana: String): String = converter.convertKanaToRomaji(kana)
}