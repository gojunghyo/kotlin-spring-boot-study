package com.example.demo.service.dto

import io.swagger.v3.oas.annotations.media.Schema
import lombok.Builder
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * 게시물 등록 DTO.
 */
@Schema(description = "게시물 등록/수정 모델")
data class BoardSource(

    @NotBlank
    @Schema(description = "제목", example = "게시물 제목", required = true)
    val boardTitle: String,

    @NotNull
    @Schema(description = "사용여부", example = "true", required = true)
    val boardUseYn: Boolean,

    @NotBlank
    @Schema(description = "내용", example = "게시물 내용", required = true)
    val boardContents: String,

    @Size(max = 3)
    @Builder.Default
    @Schema(description = "첨부파일 목록")
    val attachments: List<BoardAttachmentSource> = emptyList()
)
