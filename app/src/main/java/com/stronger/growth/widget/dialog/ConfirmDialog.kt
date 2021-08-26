package com.stronger.growth.widget.dialog

import android.content.Context
import android.view.View
import android.widget.TextView
import com.stronger.growth.R

/**
 * @author rachel.zhao on 2021/7/11.
 */
class ConfirmDialog(private val context: Context) : BaseDialog(context) {
    private lateinit var tvTitle: TextView
    private lateinit var tvCancel: TextView
    private lateinit var tvConfirm: TextView
    private var onConfirmClickListener: OnConfirmClickListener? = null
    private var onCancelClickListener: OnCancelClickListener? = null
    //宽度比例
    private var width: Double = 0.9
    override fun getDialogStyle(): Int {
        return R.style.CommonDialogStyle
    }

    override fun getDialogWidth(): Double {
        return width
    }

    fun setDialogWidth(width: Double): ConfirmDialog {
        this.width = width
        return this
    }

    override fun getLayoutId() = R.layout.dialog_confirm


    override fun onLayoutView(view: View) {
        tvTitle = view.findViewById(R.id.dialog_title)
        tvCancel = view.findViewById(R.id.cancel_btn)
        tvConfirm = view.findViewById(R.id.confirm_btn)
    }

    override fun create(): ConfirmDialog {
        super.create()
        return this
    }

    override fun show() {
        prepareShow()
        super.show()
    }

    private fun prepareShow() {
        tvConfirm.setOnClickListener {
            onConfirmClickListener?.onClick()
            dialog.dismiss()
        }
        tvCancel.setOnClickListener {
            onCancelClickListener?.onClick()
            dialog.dismiss()
        }
    }

    fun setTitle(title: String): ConfirmDialog {
        tvTitle.text = title
        tvTitle.visibility = View.VISIBLE
        return this
    }

    fun setConfirm(title: String): ConfirmDialog {
        tvConfirm.text = title
        tvConfirm.visibility = View.VISIBLE
        return this
    }

    fun setOnConfirmClickListener(onConfirmClickListener: OnConfirmClickListener): ConfirmDialog {
        this.onConfirmClickListener = onConfirmClickListener
        return this
    }

    fun setOnCancelClickListener(onCancelClickListener: OnCancelClickListener): ConfirmDialog {
        this.onCancelClickListener = onCancelClickListener
        return this
    }

    interface OnConfirmClickListener {
        fun onClick()

    }

    interface OnCancelClickListener {
        fun onClick()

    }
}