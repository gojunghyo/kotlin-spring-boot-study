package com.example.demo.service.dto

import io.swagger.v3.oas.annotations.media.Schema
import lombok.EqualsAndHashCode
import javax.validation.constraints.NotBlank

/**
 * 게시물 첨부파일 등록 DTO.
 */
@Schema(description = "게시물 첨부파일 등록 모델")
data class BoardAttachmentSource(
    /**
     * 첨부파일경로.
     */
    @NotBlank
    @EqualsAndHashCode.Include
    @Schema(description = "경로", example = "https://host.com/foo/bar/", required = true)
    val boardAttachmentPath: String,

    /**
     * 첨부파일명.
     */
    @field:NotBlank
    @field:EqualsAndHashCode.Include
    @Schema(description = "파일명", example = "image.png", required = true)
    val boardAttachmentName: String
)
