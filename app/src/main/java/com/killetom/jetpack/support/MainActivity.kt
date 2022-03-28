package com.killetom.jetpack.support

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.killetom.jetpack.permission.KTSupportPermission
import com.killetom.jetpack.support.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val ktSupportPermission = KTSupportPermission()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        val binding = ActivityMainBinding.inflate(layoutInflater)

        ktSupportPermission.attach(this)

        binding.getMultipePermission.setOnClickListener {

            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            ktSupportPermission
                .requestPermission(permissions) {

                    //理成功后逻辑
                    okAction = {}
                    //处理失败后逻辑
                    failAction = {}

                }
        }

        binding.getSinglePermission.setOnClickListener {

            ktSupportPermission
                .requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) {

                    //理成功后逻辑
                    okAction = {}
                    //处理失败后逻辑
                    failAction = {}

                }
        }

    }
}