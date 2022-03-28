package com.killetom.jetpack.permission

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.killetom.jetpack.permission.action.IMultilplePermissionCheckAction
import com.killetom.jetpack.permission.action.ISinglePermissionCheckAction
import com.killetom.jetpack.permission.helper.IPermissionHelper
import com.killetom.jetpack.permission.imp.KTSupportMultiplePermissionImp
import com.killetom.jetpack.permission.imp.KTSupportSinglePermissionImp

/**
 * create by 易庞宙(KilleTom) on 2022/03/23 12:10
 * email : 1986545332@qq.com
 * description：
 **/
class KTSupportPermission() : IPermissionHelper {

    private val multiplePermissionResultCallback = ActivityResultCallback<Map<String, Boolean>> {

        currentMultilplePermissionCheckAction?.checkAction(it)
    }

    private val singlePermissionResultCallBack = ActivityResultCallback<Boolean> {

        currentSingleCheckAction?.checkAction(it)
    }

    private var singlePermissionLauncher: ActivityResultLauncher<String>? = null
    private var multiplePermissionLauncher: ActivityResultLauncher<Array<String>>? = null

    private var currentSingleCheckAction: ISinglePermissionCheckAction? = null
    private var currentMultilplePermissionCheckAction: IMultilplePermissionCheckAction? = null


    override fun attach(activity: ComponentActivity) {

        singlePermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            singlePermissionResultCallBack
        )

        multiplePermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            multiplePermissionResultCallback
        )
    }


    override fun detach(activity: ComponentActivity) {

        singlePermissionLauncher?.unregister()
        multiplePermissionLauncher?.unregister()

        singlePermissionLauncher = null
        multiplePermissionLauncher = null
    }


    override fun attach(fragment: Fragment) {

        singlePermissionLauncher = fragment.registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            singlePermissionResultCallBack
        )

        multiplePermissionLauncher = fragment.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            multiplePermissionResultCallback
        )
    }

    override fun detach(fragment: Fragment) {

        singlePermissionLauncher?.unregister()
        multiplePermissionLauncher?.unregister()

        singlePermissionLauncher = null
        multiplePermissionLauncher = null
    }

    override fun requestPermission(
        permission: String,
        call: ISinglePermissionCheckAction.() -> Unit) {

        val imp = KTSupportSinglePermissionImp()
        currentSingleCheckAction = imp
        imp.call()

        singlePermissionLauncher?.launch(permission)

    }


    override fun requestPermission(
        permissions: Array<String>,
        call: IMultilplePermissionCheckAction.() -> Unit) {

        val imp = KTSupportMultiplePermissionImp()
        currentMultilplePermissionCheckAction = imp
        imp.call()

        multiplePermissionLauncher?.launch(permissions)
    }



}

