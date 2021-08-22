package com.stronger.growth.ui.dashboard

import androidx.lifecycle.ViewModelProviders
import com.stronger.growth.R
import com.stronger.growth.base.BaseFragment
import com.stronger.growth.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
  private lateinit var dashboardViewModel: DashboardViewModel
  override fun initData() {
    dashboardViewModel =
      ViewModelProviders.of(this).get(DashboardViewModel::class.java)
  }

  override fun getLayoutId() = R.layout.fragment_dashboard

}