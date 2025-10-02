package com.va.liontravel_test.domain.repo

import com.va.liontravel_test.domain.model.Travel

/**
 * Created by AidenChang on 2025/10/2
 */
interface TravelRepository {
    suspend fun getHomeData(): Travel
}