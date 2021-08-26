package com.stronger.growth.utils.permission


/**
 * @author rachel.zhao on 2021/6/27.
 */
sealed class PermissionResult {
    object Grant : PermissionResult()
    class Deny(val permissions: Array<String>) : PermissionResult()
    class Rationale(val permissions: Array<String>) : PermissionResult()
}