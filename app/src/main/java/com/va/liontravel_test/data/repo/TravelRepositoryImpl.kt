package com.va.liontravel_test.data.repo

import android.content.Context
import com.va.liontravel_test.data.dto.TravelDto
import com.va.liontravel_test.data.mapper.toDomain
import com.va.liontravel_test.domain.model.Travel
import com.va.liontravel_test.domain.repo.TravelRepository
import kotlinx.serialization.json.Json

/**
 * Created by AidenChang on 2025/10/2
 */
class TravelRepositoryImpl(
    private val context: Context,
    private val jsonFileName: String = "data.json"
) : TravelRepository {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    override suspend fun getHomeData(): Travel {
        val raw = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }
        val dto = json.decodeFromString<TravelDto>(raw)
        return dto.toDomain()
    }
}