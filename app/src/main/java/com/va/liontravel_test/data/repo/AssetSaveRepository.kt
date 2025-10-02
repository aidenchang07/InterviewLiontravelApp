package com.va.liontravel_test.data.repo

import android.content.Context
import com.va.liontravel_test.data.dto.HomeDto
import com.va.liontravel_test.domain.model.CarouselImage
import com.va.liontravel_test.domain.repo.SaveRepository
import kotlinx.serialization.json.Json

/**
 * Created by AidenChang on 2025/10/2
 */
class AssetSaveRepository(
    private val context: Context,
    private val jsonFileName: String = "data.json"
) : SaveRepository {
    private val json = Json {
        ignoreUnknownKeys = true      // 忽略未知欄位
        explicitNulls = false         // 略過 null 欄位的明確輸出需求
    }

    override suspend fun getCarouselImages(): List<CarouselImage> {
        val raw = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }
        val dto = json.decodeFromString<HomeDto>(raw)

        val tagList   = dto.tagData.orEmpty()
        val draftList = dto.draftData.orEmpty()

        // 1) 先找 TagSort 最小者（當作優先），找不到就取第一筆有 TagNo 的
        val tagNoKey: String = (
                tagList
                    .filter { !it.tagNo.isNullOrBlank() }
                    .minByOrNull { it.tagSort ?: Int.MAX_VALUE }
                    ?.tagNo
                    ?: tagList.firstOrNull { !it.tagNo.isNullOrBlank() }?.tagNo
                ) ?: return emptyList()

        // 2) 過濾出對應 TagNo 的圖片，確保 URL 有值且非空
        return draftList
            .asSequence()
            .filter { it.tagNo == tagNoKey }
            .mapNotNull { it.draftPic?.takeIf { url -> url.isNotBlank() } }
            .map { url -> CarouselImage(url) }
            .toList()
    }
}