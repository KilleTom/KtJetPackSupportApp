package com.killetom.jetpack.permission.imp

import com.killetom.jetpack.permission.action.ISinglePermissionCheckAction


/**
 * create by 易庞宙(KilleTom) on 2022/03/24 10:41
 * email : 1986545332@qq.com
 * description：
 **/
class KTSupportSinglePermissionImp(
    override var okAction: (() -> Unit)? = null,
    override var failAction: (() -> Unit)? = null,
) : ISinglePermissionCheckAction