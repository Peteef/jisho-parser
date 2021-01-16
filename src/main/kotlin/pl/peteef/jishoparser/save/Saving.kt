package pl.peteef.jishoparser.save

interface Saving<T> {
    fun save(data: T, filename: String)
}