package com.va.liontravel_test.domain.repo

import com.va.liontravel_test.domain.model.CarouselImage

/**
 * Created by AidenChang on 2025/10/2
 */
interface SaveRepository {
    suspend fun getCarouselImages(): List<CarouselImage>
}