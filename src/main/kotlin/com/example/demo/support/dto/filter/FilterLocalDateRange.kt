package com.example.demo.support.dto.filter

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject
import org.springframework.format.annotation.DateTimeFormat
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * 기간 필터링 조건 모델.
 */
@Schema(description = "날짜 범위 검색 모델")
@ParameterObject
data class FilterLocalDateRange(
    /**
     * 필터링 적용 시작일.
     */
    @JsonProperty(value = "startDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Parameter(description = "yyyy-MM-dd 형식의 검색 시작일 (goe)")
    var startDate: LocalDate? = null,

    /**
     * 필터링 적용 종료일.
     */
    @JsonProperty(value = "endDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Parameter(description = "yyyy-MM-dd 형식의 검색 종료일 (loe)")
    var endDate: LocalDate? = null,
) : Serializable {

    @get:JsonIgnore
    val startDateTime: LocalDateTime?
        get() = startDate?.atStartOfDay()

    @get:JsonIgnore
    val endDateTime: LocalDateTime?
        get() = endDate?.let { LocalDateTime.of(it, LocalTime.MAX) }
}