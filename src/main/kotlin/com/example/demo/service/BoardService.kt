package com.example.demo.service

import com.example.demo.persistence.dto.BoardCondition
import com.example.demo.persistence.dto.BoardDto
import com.example.demo.persistence.entity.BoardEntity
import com.example.demo.persistence.repository.BoardRepository
import com.example.demo.service.dto.BoardItem
import com.example.demo.service.dto.BoardSource
import com.example.demo.service.helper.BoardEntityRelationshipMappingComponent
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException

@Slf4j
@Service
class BoardService(private val boardRepository: BoardRepository,
                   private val boardEntityRelationshipMappingComponent: BoardEntityRelationshipMappingComponent
                   ) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun getPaginatedBoard(
        condition: BoardCondition,
        pageable: Pageable): Page<BoardItem> {
        log.info("Service IN Go Return")
        return boardRepository
            .findAllByCondition(condition, pageable)
            .map(BoardItem::of)
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun getBoard(
        boardId: Long
    ): BoardDto {
        val storedEntity : BoardEntity = getBoardEntityById(boardId)
        return BoardDto.withContentsAndAttachments(storedEntity)
    }



    fun insertBoard(source: BoardSource): BoardDto {
        val boardEntity = boardEntityRelationshipMappingComponent.buildForInsert(source)
        val storedEntity = boardRepository.save(boardEntity)
        return BoardDto.withContentsAndAttachments(storedEntity)
    }

    fun updateBoard(boardId: Long, source: BoardSource): BoardDto{
        val boardEntity = boardEntityRelationshipMappingComponent.buildForUpdate(getBoardEntityById(boardId), source)
        val storedEntity = boardRepository.save(boardEntity)

        return BoardDto.withContentsAndAttachments(storedEntity)
    }

    fun deleteBoard(boardId: Long): BoardDto {
        val boardEntity = getBoardEntityById(boardId)

        boardRepository.delete(boardEntity)

        return BoardDto.withContentsAndAttachments(boardEntity)
    }


    fun getBoardEntityById(boardId: Long): BoardEntity {
        return boardRepository
            .findByBoardId(boardId)
            ?.orElseThrow { EntityNotFoundException("[$boardId]는 존재하지 않거나, 삭제된 상태입니다.") }
    }
}

