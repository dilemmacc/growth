package com.stronger.growth.widget.dialog

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.stronger.growth.R
import com.stronger.growth.widget.dialog.BaseDialog

/**
 * @author rachel.zhao on 2021/8/11.
 * 转圈
 */
class LoadingDialog (private val context: Context) : BaseDialog(context) {
    override fun getLayoutId() = R.layout.dialog_loading
    override fun getDialogStyle(): Int {
        return R.style.CommonDialogStyle
    }
    override fun onLayoutView(view: View) {
        val imageView: ImageView = view.findViewById(R.id.iv_image)
        val animation: Animation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        animation.duration = 2000
        animation.repeatCount = 10
        animation.fillAfter = true
        imageView.startAnimation(animation)
    }
    override fun getInflateWidthContent() = true
}