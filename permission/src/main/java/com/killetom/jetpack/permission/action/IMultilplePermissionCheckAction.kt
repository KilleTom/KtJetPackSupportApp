package com.killetom.jetpack.permission.action

interface IMultilplePermissionCheckAction {

    fun checkAction(map: Map<String, Boolean>) {

        val failMultiplePermission = ArrayList<String>()

        map.forEach { entry ->

            if (!entry.value) {
                failMultiplePermission.add(entry.key)
            }
        }

        if (failMultiplePermission.isNullOrEmpty()) {

            okAction?.invoke()

            return
        }

        failAction?.invoke(failMultiplePermission)
    }

    var okAction: (() -> Unit)?

    var failAction: ((List<String>) -> Unit)?
}