package com.stronger.growth.utils.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 *
 * 相机、存储、录音权限的校验工具类
 */
object PermissionCheckHelper {
    fun hasAPPNeededPermissions(activity: FragmentActivity): Boolean {
        val pes = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
        return hasPermissions(activity, pes)
    }

    fun hasStoragePermissions(activity: FragmentActivity): Boolean {
        val pes = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return hasPermissions(activity, pes)
    }

    fun hasRecordPermissions(activity: FragmentActivity): Boolean {
        val pes = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
        return hasPermissions(activity, pes)
    }

    fun hasTakePhotoPermissions(activity: FragmentActivity): Boolean {
        val pes = arrayOf(
            Manifest.permission.CAMERA
        )
        return hasPermissions(activity, pes)
    }


    private fun hasPermissions(activity: Activity, arrays: Array<String>): Boolean {
        for (per in arrays) {
            if (!hasPermission(activity, per)) {
                return false
            }
        }
        return true
    }

    private fun hasPermission(activity: Activity, permission: String): Boolean {
        try {
            return !isAboveMarshmallow()
                    || PackageManager.PERMISSION_GRANTED ==
                    ContextCompat.checkSelfPermission(activity, permission)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun isAboveMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }
}