package com.example.demo.persistence.repository

import com.example.demo.persistence.entity.BoardEntity
import com.sun.istack.Nullable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BoardRepository : JpaRepository<BoardEntity, Long>, BoardRepositoryCustom {

    @EntityGraph(attributePaths = ["contents", "attachments"], type = EntityGraph.EntityGraphType.FETCH)
    fun findByBoardId(boardId: Long): Optional<BoardEntity>

    @EntityGraph(attributePaths = ["contents"], type = EntityGraph.EntityGraphType.FETCH)
    fun findAllByBoardIdIn(boardIds: Collection<Long>): List<BoardEntity>
}