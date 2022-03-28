package com.killetom.jetpack.permission.imp

import com.killetom.jetpack.permission.action.IMultilplePermissionCheckAction


/**
 * create by 易庞宙(KilleTom) on 2022/03/24 10:41
 * email : 1986545332@qq.com
 * description：
 **/
class KTSupportMultiplePermissionImp(
    override var okAction: (() -> Unit)?=null,
    override var failAction: ((List<String>) -> Unit)?=null,
) : IMultilplePermissionCheckAction