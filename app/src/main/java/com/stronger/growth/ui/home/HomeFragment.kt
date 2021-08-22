package com.stronger.growth.ui.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stronger.growth.R
import com.stronger.growth.base.BaseFragment
import com.stronger.growth.databinding.FragmentHomeBinding
import com.stronger.growth.utils.DateUtil

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
  private lateinit var homeViewModel: HomeViewModel

  override fun initData() {
    homeViewModel =
      ViewModelProviders.of(this).get(HomeViewModel::class.java)
    homeViewModel.text.observe(this, Observer {
    })
    mBinding?.run {
      tvTodayTask.text = DateUtil.getToday()
    }
  }

  override fun getLayoutId() = R.layout.fragment_home

}