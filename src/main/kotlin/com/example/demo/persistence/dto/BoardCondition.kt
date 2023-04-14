package com.example.demo.persistence.dto

import com.example.demo.support.dto.filter.FilterLocalDateRange
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springdoc.api.annotations.ParameterObject
import javax.validation.Valid


/**
 * 게시물 필터.
 */
@Schema(description = "게시판 목록 조회 조건 정보 모델")
@ParameterObject
data class BoardCondition(

    @Parameter(description = "제목")
    val title: String? = null,

    @Parameter(description = "내용")
    val contents: String? = null,

    @field:Valid
    @Parameter(description = "최종 수정일 범위")
    val lastModifiedPeriod: FilterLocalDateRange? = null
)
