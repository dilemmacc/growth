package com.stronger.growth.utils.permission

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData

/**
 * @author rachel.zhao on 2021/6/27.
 */
class LivePermissions {

    companion object {
        const val TAG = "permissions"
    }

    constructor(activity: AppCompatActivity) {
        liveFragment = getInstance(activity.supportFragmentManager)
    }

    constructor(fragment: Fragment) {
        liveFragment = getInstance(fragment.childFragmentManager)
    }

    @Volatile
    private var liveFragment: LiveFragment? = null

    private fun getInstance(fragmentManager: FragmentManager) =
        liveFragment ?: synchronized(this) {
            liveFragment ?: if (fragmentManager.findFragmentByTag(TAG) == null) LiveFragment().run {
                fragmentManager.beginTransaction().add(this, TAG).commitNow()
                this
            } else fragmentManager.findFragmentByTag(TAG) as LiveFragment
        }

    fun request(vararg permissions: String): MutableLiveData<PermissionResult> {
        return this.requestArray(permissions)
    }

    fun requestArray(permissions: Array<out String>): MutableLiveData<PermissionResult> {
        liveFragment!!.requestPermissions(permissions)
        return liveFragment!!.liveData
    }

}