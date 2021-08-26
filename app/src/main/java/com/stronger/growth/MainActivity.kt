package com.stronger.growth

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stronger.growth.base.BaseActivity
import com.stronger.growth.databinding.ActivityMainBinding
import com.stronger.growth.utils.ExcelOperator
import com.stronger.growth.utils.FilePathUtil
import com.stronger.growth.utils.permission.LivePermissions
import com.stronger.growth.utils.permission.PermissionCheckHelper
import com.stronger.growth.utils.permission.PermissionResult

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initData(savedInstanceState: Bundle?) {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //读取文件
        if (!PermissionCheckHelper.hasStoragePermissions(this)) {
            LivePermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).observe(this) {
                when (it) {
                    is PermissionResult.Grant -> {
                        //权限允许
                        init()
                    }
                    is PermissionResult.Rationale -> {  //权限拒绝

                    }
                    is PermissionResult.Deny -> {   //权限拒绝，且勾选了不再询问

                    }
                }
            }
        } else {
            init()
        }
    }

    private fun init() {
        val xmlPath = FilePathUtil.getXMLPath()
        ExcelOperator.readXml(xmlPath)
    }

    override fun getLayoutId() = R.layout.activity_main
}
