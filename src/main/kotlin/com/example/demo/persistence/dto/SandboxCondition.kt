package com.example.demo.persistence.dto

import com.example.demo.persistence.code.SandboxCategory
import com.example.demo.support.dto.filter.FilterNumericRange
import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject

/**
 * 데이터 필터.
 */
@ParameterObject
data class SandboxCondition(

    /**
     * 이름.
     */
    @Schema(description = "이름")
    val name: String? = null,

    /**
     * 나이.
     */
    @Schema(description = "나이 범위")
    val age: FilterNumericRange? = null,

    /**
     * 유형.
     */
    @Schema(description = "카테고리")
    val category: SandboxCategory? = null
)
