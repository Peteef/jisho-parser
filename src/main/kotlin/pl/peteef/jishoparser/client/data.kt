package pl.peteef.jishoparser.client

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

data class PageEntity(
    val data: Set<Position>
) {
    object Deserializer : ResponseDeserializable<PageEntity> {
        private val gson: Gson

        init {
            val gsonBuilder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            gson = gsonBuilder.create()
        }

        override fun deserialize(content: String): PageEntity {
            return gson.fromJson(content, PageEntity::class.java)
        }
    }
}

data class Position(
    val slug: String,
    val japanese: List<JapaneseWord>,
    val senses: List<Definition>
)

data class JapaneseWord(
    val word: String,
    val reading: String
)

data class Definition(
    val englishDefinitions: Set<String>,
    val partsOfSpeech: Set<String>
)