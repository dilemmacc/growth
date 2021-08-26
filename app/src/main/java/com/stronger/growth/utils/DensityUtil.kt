package com.stronger.growth.utils

import android.content.Context
import android.util.TypedValue
import com.stronger.growth.GlobalData

/**
 * @author rachel.zhao on 2021/8/23.
 */
object DensityUtil {
    fun dp2px(dpValue: Float): Int {
        return dp2px(GlobalData.context, dpValue)
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun dp2sp(sp: Float): Int {
        return dp2sp(GlobalData.context, sp)
    }

    /**
     * Converts sp to px
     *
     * @param context Context
     * @param sp the value in sp
     * @return int
     */
    fun dp2sp(context: Context, sp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, sp,
            context.resources.displayMetrics
        ).toInt()
    }

    fun px2dp(pxValue: Float): Int {
        return px2dp(GlobalData.context, pxValue)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun px2sp(pxValue: Float): Int {
        return px2sp(GlobalData.context, pxValue)
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Int {
        return sp2px(GlobalData.context, spValue)
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}