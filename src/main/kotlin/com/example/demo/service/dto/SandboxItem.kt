package com.example.demo.service.dto

import com.example.demo.persistence.code.SandboxCategory
import com.example.demo.persistence.dto.SandboxDto
import io.swagger.v3.oas.annotations.media.Schema
import lombok.EqualsAndHashCode
import java.time.LocalDateTime

/**
 * 데이터 항목.
 */
@Schema(description = "게시판 항목 정보 응답 모델")
data class SandboxItem(
    @Schema(description = "식별자", example = "1", required = true)
    @field:EqualsAndHashCode.Include
    val id: Long,

    @Schema(description = "이름", example = "이름", required = true)
    val name: String,

    @Schema(description = "나이", example = "19", required = true)
    val age: Int,

    @Schema(description = "유형", example = "NORMAL", required = true)
    val category: SandboxCategory,

    @Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
    val updateDate: LocalDateTime,
) {

    companion object {
        fun of(sandboxDto: SandboxDto): SandboxItem = SandboxItem(
            id = sandboxDto.id,
            name = sandboxDto.name,
            age = sandboxDto.age,
            category = sandboxDto.category,
            updateDate = sandboxDto.updateDate
        )
    }
}
