package com.va.liontravel_test.data

import android.content.Context
import com.va.liontravel_test.data.repo.AssetSaveRepository
import com.va.liontravel_test.domain.repo.SaveRepository

/**
 * Created by AidenChang on 2025/10/6
 */
class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    override val saveRepository: SaveRepository by lazy {
        AssetSaveRepository(context)
    }
}