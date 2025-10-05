package com.va.liontravel_test

import android.app.Application
import com.va.liontravel_test.data.repo.AssetSaveRepository

/**
 * Created by AidenChang on 2025/10/5
 */
class MyApp : Application() {
    val homeRepo by lazy { AssetSaveRepository(applicationContext) }
}