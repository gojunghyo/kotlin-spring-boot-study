package com.example.demo.persistence.dto

import com.example.demo.persistence.entity.BoardEntity
import lombok.EqualsAndHashCode

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
data class BoardContentsDto(

    /**
     * 게시물 번호.
     */
    @EqualsAndHashCode.Include
    val boardId: Long? = null,

    /**
     * 게시물 컨텐츠 내용.
     */
    val boardContents: String? = null

) {

    constructor(boardEntity: BoardEntity) : this(
        boardId = boardEntity.contents?.boardId,
        boardContents = boardEntity.contents?.boardContents
    )

}