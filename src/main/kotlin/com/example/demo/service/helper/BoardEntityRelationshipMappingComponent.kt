package com.example.demo.service.helper

import com.example.demo.persistence.entity.BoardAttachmentEntity
import com.example.demo.persistence.entity.BoardContentsEntity
import com.example.demo.persistence.entity.BoardEntity
import com.example.demo.service.dto.BoardAttachmentSource
import com.example.demo.service.dto.BoardSource
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.stream.Collectors

@Component
class BoardEntityRelationshipMappingComponent {

    private val log = LoggerFactory.getLogger(javaClass)
    fun buildForInsert(boardSource: BoardSource): BoardEntity {
        val boardEntity = buildBoardEntity(boardSource)

        val boardContentsEntity = buildBoardContentsEntity(boardEntity, boardSource.boardContents)
        val boardAttachmentEntities = buildBoardAttachmentEntities(boardEntity, boardSource.attachments)

        boardEntity.contents = boardContentsEntity
        boardEntity.attachments = boardAttachmentEntities as MutableSet<BoardAttachmentEntity>

        return boardEntity
    }

    fun buildForUpdate(boardEntity: BoardEntity, boardSource: BoardSource): BoardEntity {
        val boardAttachmentEntities = buildBoardAttachmentEntities(boardEntity, boardSource.attachments)

        boardEntity.boardTitle = boardSource.boardTitle
        boardEntity.boardUseYn = boardSource.boardUseYn
        boardEntity.updateDate = LocalDateTime.now()

        boardEntity.contents.boardContents =  boardSource.boardContents
        boardEntity.attachments.clear()
        boardEntity.attachments.addAll(boardAttachmentEntities)

        return boardEntity
    }

    private fun buildBoardEntity(boardSource: BoardSource): BoardEntity {
        log.info("check Source = {}", boardSource)
        val ret = BoardEntity()
        ret.boardTitle =  boardSource.boardTitle
        ret.boardUseYn =  boardSource.boardUseYn

        return ret
    }

    private fun buildBoardContentsEntity(boardEntity: BoardEntity, contents: String): BoardContentsEntity {
        var ret = BoardContentsEntity()
        ret.board = boardEntity
        ret.boardContents = contents

        return ret
    }

    private fun buildBoardAttachmentEntities(
        boardEntity: BoardEntity,
        attachments: List<BoardAttachmentSource>
    ): Set<BoardAttachmentEntity> {
        return attachments
            .stream()
            .map { attachment ->
                BoardAttachmentEntity(
                    board = boardEntity,
                    boardAttachmentPath = attachment.boardAttachmentPath,
                    boardAttachmentName = attachment.boardAttachmentName
                )
            }
            .collect(Collectors.toSet())
    }


}
