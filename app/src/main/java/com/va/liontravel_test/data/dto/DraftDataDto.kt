package com.va.liontravel_test.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AidenChang on 2025/10/2
 */
@Serializable
data class DraftDataDto(
    @SerialName("TagNo") val tagNo: String?,
    @SerialName("DraftTitle") val draftTitle: String?,
    @SerialName("DraftSubTitle") val draftSubTitle: String?,
    @SerialName("DraftPic") val draftPic: String?
)
