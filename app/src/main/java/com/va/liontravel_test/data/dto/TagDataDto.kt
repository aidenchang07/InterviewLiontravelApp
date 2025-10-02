package com.va.liontravel_test.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AidenChang on 2025/10/2
 */
@Serializable
data class TagDataDto(
    @SerialName("TagNo") val tagNo: String?,
    @SerialName("TagTitle") val tagTitle: String?,
    @SerialName("TagSort") val tagSort: Int?
)
