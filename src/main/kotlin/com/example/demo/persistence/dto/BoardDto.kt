package com.example.demo.persistence.dto
import com.example.demo.persistence.entity.BoardEntity
import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

import io.swagger.v3.oas.annotations.media.Schema
import lombok.EqualsAndHashCode



/**
 * 특정 게시물 정보 응답 모델.
 */
@Schema(description = "특정 게시물 정보 응답 모델")
data class BoardDto(

    @Schema(description = "식별자", example = "1", required = true)
    @field:EqualsAndHashCode.Include
    val boardId: Long,

    @Schema(description = "제목", example = "게시물 제목", required = true)
    val boardTitle: String,

    @Schema(description = "사용여부", example = "true", required = true)
    val boardUseYn: Boolean,

    @Schema(description = "생성일", example = "yyyy-MM-dd", required = true)
    val createDate: LocalDateTime,

    @Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
    val updateDate: LocalDateTime,

    @Schema(description = "내용", example = "게시물 내용", required = true)
    var contents: BoardContentsDto? = null,

    @Schema(description = "첨부파일 목록")
    var attachments: List<BoardAttachmentDto>? = null,

    ) {
    @QueryProjection
    constructor(boardEntity: BoardEntity) : this(
        boardEntity.boardId,
        boardEntity.boardTitle,
        boardEntity.boardUseYn,
        boardEntity.createDate,
        boardEntity.updateDate
    )

    companion object {
        fun withContents(boardEntity: BoardEntity): BoardDto {
            val boardDto = BoardDto(boardEntity)
            val boardContentsDto = BoardContentsDto(boardEntity)
            boardDto.contents = boardContentsDto
            return boardDto
        }

        fun withContentsAndAttachments(boardEntity: BoardEntity): BoardDto {
            val boardDto = withContents(boardEntity)
            val boardAttachmentDtoList = collectAttachmentDtoList(boardEntity)
            boardDto.attachments = boardAttachmentDtoList
            return boardDto
        }

        private fun collectAttachmentDtoList(boardEntity: BoardEntity): List<BoardAttachmentDto> {
            return boardEntity.attachments
                .map(::BoardAttachmentDto)
                .distinct()
        }
    }
}
