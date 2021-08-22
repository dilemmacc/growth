package com.stronger.growth.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.fuusy.common.support.StatusBar

/**
 *
 * @author ruichao
 * 2021-08-22 10:44
 */

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity {

  constructor() : super()


  var mBinding: T? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    StatusBar().fitSystemBar(this)
    mBinding = DataBindingUtil.setContentView(this, getLayoutId())
    initData(savedInstanceState)

  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding?.unbind()
  }

  abstract fun initData(savedInstanceState: Bundle?)

  abstract fun getLayoutId(): Int

}
