package com.example.demo.persistence.repository

import com.example.demo.persistence.CustomQuerydslRepositorySupport
import com.example.demo.persistence.dto.BoardCondition
import com.example.demo.persistence.dto.BoardDto
import com.example.demo.persistence.dto.QBoardDto
import com.example.demo.persistence.entity.BoardEntity
import com.example.demo.persistence.entity.QBoardEntity
import com.example.demo.query.QueryDslHelper.Companion.optionalWhen
import com.querydsl.jpa.JPAExpressions.select
import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

  class BoardRepositoryCustomImpl() : CustomQuerydslRepositorySupport(BoardEntity::class.java), BoardRepositoryCustom {
      private val log = LoggerFactory.getLogger(javaClass)

      companion object {
        private val BOARD_ENTITY = QBoardEntity.boardEntity
    }

    override fun findAllByCondition(condition: BoardCondition, pageable: Pageable): Page<BoardDto> {
        log.info("[IN] findAllByCondition()")

        val query = getPaginationDefaultQuery()
        log.info("query = {}", query)

        applyPaginationWhereClause(query, condition)
        val queryResults = querydsl.applyPagination(pageable, query).fetchResults()

        return PageImpl(queryResults.results, pageable, queryResults.total)
    }

    private fun getPaginationDefaultQuery(): JPQLQuery<BoardDto> {
        log.info("[IN] getPaginationDefaultQuery()")
        val builder = JPAQueryFactory(entityManager)
        return builder
            .select(QBoardDto(BOARD_ENTITY))
            .from(BOARD_ENTITY)
            .innerJoin(BOARD_ENTITY.contents)
            .fetchJoin()
    }

    private fun applyPaginationWhereClause(query: JPQLQuery<BoardDto>, condition: BoardCondition) {
        optionalWhen(condition.title)?.then { it ->
                query.where(BOARD_ENTITY.boardTitle.trim().containsIgnoreCase(it))
        }
//        optionalWhen(condition.contents) {
//            query.where(BOARD_ENTITY.contents.boardContents.trim().containsIgnoreCase(it))
//        }
//        optionalWhen(condition.lastModifiedPeriod) {
//            query.where(
//                BOARD_ENTITY.updateDate.goe(it.startDateTime),
//                BOARD_ENTITY.updateDate.loe(it.endDateTime)
//            )
//        }
    }
}
