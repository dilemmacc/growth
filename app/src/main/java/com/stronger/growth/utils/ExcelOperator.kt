package com.stronger.growth.utils

import android.content.Context
import android.view.View
import com.stronger.growth.GlobalData
import org.apache.poi.hssf.usermodel.HSSFDateUtil
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.util.WorkbookUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat

/**
 *
 * @author ruichao
 * 2021-08-22 22:41
 */
object ExcelOperator {

  fun onReadClick(view: View) {
//    printlnToUser("reading XLSX file from resources")
//    val stream = getResources().openRawResource(R.raw.test1)
    val stream = FilePathUtil.getCachePath()
    try {
      val workbook = XSSFWorkbook(stream)
      val sheet = workbook.getSheetAt(0)
      val rowsCount = sheet.getPhysicalNumberOfRows()
      val formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator()
      for (r in 0 until rowsCount) {
        val row = sheet.getRow(r)
        val cellsCount = row.getPhysicalNumberOfCells()
        for (c in 0 until cellsCount) {
          val value = getCellAsString(row, c, formulaEvaluator)
          val cellInfo = "r:$r; c:$c; v:$value"
//          printlnToUser(cellInfo)
        }
      }
    } catch (e: Exception) {
      /* proper exception handling to be here */
//      printlnToUser(e.toString())
    }

  }

  fun onWriteClick(view: View) {
//    printlnToUser("writing xlsx file")
    val workbook = XSSFWorkbook()
    val sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("mysheet"))
    for (i in 0..9) {
      val row = sheet.createRow(i)
      val cell = row.createCell(0)
      cell.setCellValue(i.toDouble())
    }
    val outFileName = "filetoshare.xlsx"
    try {
//      printlnToUser("writing file $outFileName")
      val cacheDir = FilePathUtil.getCachePath()
      val outFile = File(cacheDir, outFileName)
      val outputStream = FileOutputStream(outFile.absolutePath)
      workbook.write(outputStream)
      outputStream.flush()
      outputStream.close()
//      printlnToUser("sharing file...")
      share(outFileName, GlobalData.context!!)
    } catch (e: Exception) {
      /* proper exception handling to be here */
//      printlnToUser(e.toString())
    }

  }

  private fun getCellAsString(row: Row, c: Int, formulaEvaluator: FormulaEvaluator): String {
    var value = ""
    try {
      val cell = row.getCell(c)
      val cellValue = formulaEvaluator.evaluate(cell)
      when (cellValue.cellType) {
        Cell.CELL_TYPE_BOOLEAN -> value = "" + cellValue.booleanValue
        Cell.CELL_TYPE_NUMERIC -> {
          val numericValue = cellValue.numberValue
          if (HSSFDateUtil.isCellDateFormatted(cell)) {
            val date = cellValue.numberValue
            val formatter = SimpleDateFormat("dd/MM/yy")
            value = formatter.format(HSSFDateUtil.getJavaDate(date))
          } else {
            value = "" + numericValue
          }
        }
        Cell.CELL_TYPE_STRING -> value = "" + cellValue.stringValue
      }
    } catch (e: NullPointerException) {
      /* proper error handling should be here */
//      printlnToUser(e.toString())
    }

    return value
  }

  /**
   * print line to the output TextView
   * @param str
   */
//  private fun printlnToUser(str: String) {
//    if (output.length() > 8000) {
//      var fullOutput: CharSequence = output.getText()
//      fullOutput = fullOutput.subSequence(5000, fullOutput.length)
//      output.setText(fullOutput)
//      output.setSelection(fullOutput.length)
//    }
//    output.append(str + "\n")
//  }

  fun share(fileName: String, context: Context) {
//    val fileUri = Uri.parse("content://" + getPackageName() + "/" + fileName)
//    printlnToUser("sending $fileUri ...")
//    val shareIntent = Intent()
//    shareIntent.action = Intent.ACTION_SEND
//    shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
//    shareIntent.type = "application/octet-stream"
//    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)))
  }
}