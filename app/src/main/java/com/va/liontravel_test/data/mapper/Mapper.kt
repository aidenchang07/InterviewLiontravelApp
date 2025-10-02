package com.va.liontravel_test.data.mapper

import com.va.liontravel_test.data.dto.DraftDataDto
import com.va.liontravel_test.data.dto.TravelDto
import com.va.liontravel_test.data.dto.TagDataDto
import com.va.liontravel_test.domain.model.DraftData
import com.va.liontravel_test.domain.model.Travel
import com.va.liontravel_test.domain.model.TagData

/**
 * Created by AidenChang on 2025/10/5
 */

fun TravelDto.toDomain(): Travel {
    return Travel(
        headTitle = headTitle.orEmpty(),
        tagData = tagData?.map { it.toDomain() } ?: emptyList(),
        draftData = draftData?.map { it.toDomain() } ?: emptyList()
    )
}

fun TagDataDto.toDomain(): TagData {
    return TagData(
        tagNo = tagNo.orEmpty(),
        tagTitle = tagTitle.orEmpty(),
        tagSort = tagSort ?: 0
    )
}

fun DraftDataDto.toDomain(): DraftData {
    return DraftData(
        tagNo = tagNo.orEmpty(),
        draftTitle = draftTitle.orEmpty(),
        draftSubTitle = draftSubTitle.orEmpty(),
        draftPic = draftPic.orEmpty()
    )
}