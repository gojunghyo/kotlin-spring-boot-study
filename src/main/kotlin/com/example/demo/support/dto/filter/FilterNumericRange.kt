package com.example.demo.support.dto.filter

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 숫자 필터링 조건 모델.
 */
data class FilterNumericRange(

    /**
     * 필터링 적용 최소 수.
     */
    @JsonProperty(value = "min")
    @Schema(description = "검색 최소 수")
    val min: BigDecimal = BigDecimal.ZERO,

    /**
     * 필터링 적용 최대 수.
     */
    @JsonProperty(value = "max")
    @Schema(description = "검색 최대 수")
    val max: BigDecimal = BigDecimal.ZERO
)
