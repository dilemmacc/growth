package com.stronger.growth.widget.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import com.stronger.growth.R
import com.stronger.growth.widget.dialog.BaseDialog


abstract class BottomDialog(context: Context) : BaseDialog(context) {

    override fun getDialogStyle(): Int {
        return R.style.ActionSheetDialogStyle
    }

    override fun onLayoutViewBefore(dialog: Dialog) {
        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)
        val layoutParams = window?.attributes
        layoutParams?.x = 0
        layoutParams?.y = 0
        window?.attributes = layoutParams
    }
}