package com.stronger.growth.data

/**
 * @author rachel.zhao on 2021/8/23.
 */
data class ExcelData(val dataMap: MutableMap<String, MutableList<ExcelItem>>)
data class ExcelItem(
    val detail: String,
    val source: String?,
    val tag:String?,
    val status: String?,
    val finishDate: String?
)
