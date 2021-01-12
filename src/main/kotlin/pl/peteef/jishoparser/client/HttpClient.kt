package pl.peteef.jishoparser.client

import com.github.kittinunf.fuel.httpGet

object HttpClient {
    private const val HASH_CODE = "%23"
    private const val BASE_PATH = "https://jisho.org/api/v1"

    fun get(jlptLevel: JlptLevel): List<PageEntity> {
        return getAllPages(composeUrl(jlptLevel))
    }

    private fun composeUrl(jlptLevel: JlptLevel) = "$BASE_PATH/search/words?keyword=$HASH_CODE${jlptLevel.value}"

    private fun getAllPages(url: String): List<PageEntity> {
        return generateSequence(1) { it + 1 }
            .map { getPage(url, it) }
            .takeWhile { it.data.isNotEmpty() }
            .toList()
    }

    private fun getPage(url: String, page: Int): PageEntity {
        val (_, _, result) = "$url&page=$page".httpGet()
            .responseObject(PageEntity.Deserializer)
        return result.get()
    }
}