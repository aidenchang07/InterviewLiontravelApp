package com.va.liontravel_test.data

import android.content.Context
import com.va.liontravel_test.data.repo.TravelRepositoryImpl
import com.va.liontravel_test.domain.repo.TravelRepository

/**
 * Created by AidenChang on 2025/10/6
 */
class DefaultAppContainer(
    private val context: Context
) : AppContainer {
    override val travelRepository: TravelRepository by lazy {
        TravelRepositoryImpl(context)
    }
}