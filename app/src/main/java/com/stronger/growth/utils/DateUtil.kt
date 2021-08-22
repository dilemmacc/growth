package com.stronger.growth.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 *
 * @author ruichao
 * 2021-08-22 11:43
 */
object DateUtil {
  /**
   * 今天的年-月-日 星期x
   */
  fun getToday(): String {
    val simpleDateFormat = SimpleDateFormat("y年M月d日 E", Locale.CHINA)
    val calendar = Calendar.getInstance(Locale.CHINA)
    calendar.firstDayOfWeek = Calendar.MONDAY
    calendar.timeInMillis = System.currentTimeMillis()
    return simpleDateFormat.format(calendar.time)
  }
}