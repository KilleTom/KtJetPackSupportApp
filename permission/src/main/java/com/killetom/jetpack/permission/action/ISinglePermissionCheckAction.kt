package com.killetom.jetpack.permission.action

interface ISinglePermissionCheckAction {

    fun checkAction(result: Boolean) {

        if (result) {
            okAction?.invoke()
            return
        }

        failAction?.invoke()
    }

    var okAction: (() -> Unit)?

    var failAction: (() -> Unit)?
}