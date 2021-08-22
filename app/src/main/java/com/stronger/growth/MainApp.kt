package com.stronger.growth

import android.app.Application

/**
 *
 * @author ruichao
 * 2021-08-22 22:44
 */
class MainApp:Application() {
  override fun onCreate() {
    super.onCreate()
    GlobalData.context = this
  }
}