package pl.peteef.jishoparser.save

import org.apache.poi.ss.usermodel.Workbook
import java.io.FileOutputStream

object ExcelFileSaver : Saving<Workbook> {
    private const val EXTENSION = "xlsx"

    override fun save(data: Workbook, filename: String) {
        val outputStream = FileOutputStream("$filename.$EXTENSION")
        data.write(outputStream)
        data.close()
    }
}