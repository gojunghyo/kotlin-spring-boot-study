package com.example.demo.persistence.dto

import com.example.demo.persistence.code.SandboxCategory
import com.example.demo.persistence.entity.SandboxEntity
import com.querydsl.core.annotations.QueryProjection
import io.swagger.v3.oas.annotations.media.Schema
import lombok.EqualsAndHashCode
import java.time.LocalDateTime

@Schema(description = "특정 게시물 정보 응답 모델")
data class SandboxDto(
    @Schema(description = "식별자", example = "1", required = true)
    @field:EqualsAndHashCode.Include
    val id: Long,

    @Schema(description = "이름", example = "이름", required = true)
    val name: String,

    @Schema(description = "나이", example = "19", required = true)
    val age: Int,

    @Schema(description = "유형", example = "NORMAL", required = true)
    val category: SandboxCategory,

    @Schema(description = "삭제여부", example = "false", required = true)
    val deleted: Boolean,

    @Schema(description = "생성일", example = "yyyy-MM-dd", required = true)
    val createDate: LocalDateTime,

    @Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
    val updateDate: LocalDateTime
) {
    @QueryProjection
    constructor(entity: SandboxEntity) : this(
        id = entity.id,
        name = entity.name,
        age = entity.age,
        category = entity.category,
        deleted = entity.deleted,
        createDate = entity.createDate,
        updateDate = entity.updateDate
    )
}
