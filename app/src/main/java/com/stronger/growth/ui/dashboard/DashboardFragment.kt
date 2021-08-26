package com.stronger.growth.ui.dashboard

import androidx.lifecycle.ViewModelProviders
import com.stronger.growth.R
import com.stronger.growth.adapter.DashBoardAdapter
import com.stronger.growth.base.BaseFragment
import com.stronger.growth.databinding.FragmentDashboardBinding
import com.stronger.growth.utils.DensityUtil
import com.stronger.growth.widget.recyclerview.GridSpacingItemDecoration

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private var mAdapter: DashBoardAdapter? = null
    override fun initData() {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        mBinding?.run {
            mAdapter = DashBoardAdapter()
            rvTaskList.adapter = mAdapter
            rvTaskList.addItemDecoration(GridSpacingItemDecoration(3, DensityUtil.dp2px(5f), true))
//            mAdapter.submitList()
        }
    }

    override fun getLayoutId() = R.layout.fragment_dashboard

}