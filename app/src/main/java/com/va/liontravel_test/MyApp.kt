package com.va.liontravel_test

import android.app.Application
import com.va.liontravel_test.data.AppContainer
import com.va.liontravel_test.data.DefaultAppContainer

/**
 * Created by AidenChang on 2025/10/5
 */
class MyApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}