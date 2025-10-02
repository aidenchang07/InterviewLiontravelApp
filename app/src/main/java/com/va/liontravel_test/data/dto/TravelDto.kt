package com.va.liontravel_test.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AidenChang on 2025/10/2
 */
@Serializable
data class TravelDto(
    @SerialName("HeadTitle") val headTitle: String?,
    @SerialName("TagData") val tagData: List<TagDataDto>?,
    @SerialName("DraftData") val draftData: List<DraftDataDto>?
)
