package com.example.demo.persistence.dto

import com.example.demo.persistence.entity.BoardAttachmentEntity
import io.swagger.v3.oas.annotations.media.Schema

data class BoardAttachmentDto(
    @Schema(description = "첨부파일 식별자", example = "1", required = true)
    val boardAttachmentId: Long,

    @Schema(description = "소속 게시물 식별자", example = "1", required = true)
    val boardId: Long,

    @Schema(description = "경로", example = "https://host.com/foo/bar/", required = true)
    val boardAttachmentPath: String?,

    @Schema(description = "파일명", example = "image.png", required = true)
    val boardAttachmentName: String?
) {

    constructor(entity: BoardAttachmentEntity) : this(
        boardAttachmentId = entity.boardAttachmentId,
        boardId = entity.board.boardId,
        boardAttachmentPath = entity.boardAttachmentPath,
        boardAttachmentName = entity.boardAttachmentName
    )
}