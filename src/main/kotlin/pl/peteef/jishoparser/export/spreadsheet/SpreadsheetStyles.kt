package pl.peteef.jishoparser.export.spreadsheet

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.VerticalAlignment
import org.apache.poi.ss.usermodel.Workbook

class SpreadsheetStyles(private val workbook: Workbook) {
    val headerStyle = headerStyle()
    val readingStyle = readingStyle()
    val wordStyle = wordStyle()
    val bulletStyle = bulletStyle()

    private fun headerStyle(): CellStyle {
        val style = workbook.createCellStyle()
        style.verticalAlignment = VerticalAlignment.CENTER

        val font = workbook.createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 24
        font.bold = true
        style.setFont(font)

        return style
    }

    private fun readingStyle(): CellStyle {
        val style = workbook.createCellStyle()
        style.verticalAlignment = VerticalAlignment.CENTER
        style.wrapText = true

        val font = workbook.createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 18
        font.bold = true
        style.setFont(font)

        return style
    }

    private fun wordStyle(): CellStyle {
        val style = workbook.createCellStyle()
        style.verticalAlignment = VerticalAlignment.CENTER
        style.wrapText = true

        val font = workbook.createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 18
        style.setFont(font)

        return style
    }

    private fun bulletStyle(): CellStyle {
        val style = workbook.createCellStyle()
        style.verticalAlignment = VerticalAlignment.CENTER
        style.wrapText = true

        val font = workbook.createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 14
        style.setFont(font)

        return style
    }
}