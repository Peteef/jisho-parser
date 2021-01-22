package pl.peteef.jishoparser.client

import com.google.gson.annotations.SerializedName

enum class JlptLevel(val value: String) {
    @SerializedName("JLPT-N5") N5("jlpt-n5"),
    @SerializedName("JLPT-N4") N4("jlpt-n4"),
    @SerializedName("JLPT-N3") N3("jlpt-n3"),
    @SerializedName("JLPT-N2") N2("jlpt-n2"),
    @SerializedName("JLPT-N1") N1("jlpt-n1")
}