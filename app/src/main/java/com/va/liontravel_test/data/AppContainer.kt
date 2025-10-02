package com.va.liontravel_test.data

import com.va.liontravel_test.domain.repo.TravelRepository

/**
 * Created by AidenChang on 2025/10/6
 */
interface AppContainer {
    val travelRepository: TravelRepository
}