package com.stronger.growth.utils

import android.os.Environment
import android.text.TextUtils
import com.stronger.growth.GlobalData
import java.io.File

/**
 *
 * @author ruichao
 * 2021-08-22 22:46
 */

object FilePathUtil {
    const val APP = "growth"
    const val XML = "growth_track.xlsx"
    const val XML_BACKUP = "growth_track_backup.xlsx"

    /**
     * xml目录
     */
    fun getXMLPath(): String {
        return getExternalDataPkgDirPath() + File.separator +  XML
    }

    fun getXMLPath_Backup(): String {
        return getExternalDataPkgDirPath() + File.separator +XML_BACKUP
    }


    /**
     * 3\/storage/emulated/0/Android/data/包名
     */
    private fun getExternalDataPkgDirPath(): String? {
        val context = GlobalData.context
        val state = Environment.getExternalStorageState()
        val filesDir: File? = try {
            if (Environment.MEDIA_MOUNTED == state) {
                context.getExternalFilesDir(null)
            } else {
                context.filesDir
            }
        } catch (e: Exception) {
            e.printStackTrace()
            context.filesDir
        }
        return if (filesDir != null) filesDir.absolutePath else ""
    }

    /**
     * 内部cache目录
     *
     *
     * 4\/storage/emulated/0/Android/data/包名/cache
     */
    private fun getExternalCacheDir(): String? {
        val file: File? = GlobalData.context?.externalCacheDir ?: null
        return if (file != null) file.absolutePath else ""
    }

}