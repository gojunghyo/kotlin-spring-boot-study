package com.example.demo.service.dto

import com.example.demo.persistence.dto.BoardDto
import io.swagger.v3.oas.annotations.media.Schema
import lombok.Builder
import lombok.EqualsAndHashCode
import java.time.LocalDateTime

/**
 * 게시물 항목.
 */
@Schema(description = "게시판 항목 정보 응답 모델")
data class BoardItem(
    @Schema(description = "식별자", example = "1", required = true)
    @field:EqualsAndHashCode.Include
    val boardId: Long,

    @Schema(description = "제목", example = "게시물 제목", required = true)
    val boardTitle: String,

    @Schema(description = "사용여부", example = "true", required = true)
    val boardUseYn: Boolean,

    @Schema(description = "등록일", example = "yyyy-MM-dd", required = true)
    val createDate: LocalDateTime,

    @Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
    val updateDate: LocalDateTime,
) {
    companion object {
        fun of(boardDto: BoardDto) = BoardItem(
            boardId = boardDto.boardId,
            boardTitle = boardDto.boardTitle,
            boardUseYn = boardDto.boardUseYn,
            createDate = boardDto.createDate,
            updateDate = boardDto.updateDate
        )
    }
}