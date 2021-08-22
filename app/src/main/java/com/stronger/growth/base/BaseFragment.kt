package com.stronger.growth.base

/**
 *
 * @author ruichao
 * 2021-08-22 10:46
 */
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

  var mBinding: T? = null
  private lateinit var mContext: Context

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
    return mBinding?.root
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    mContext = context
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initData()
  }

  abstract fun initData()

  override fun onDestroy() {
    super.onDestroy()
    mBinding?.unbind()
  }

  abstract fun getLayoutId(): Int

}