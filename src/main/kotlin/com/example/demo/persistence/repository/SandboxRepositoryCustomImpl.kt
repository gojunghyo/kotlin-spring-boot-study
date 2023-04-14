package com.example.demo.persistence.repository

import com.example.demo.persistence.CustomQuerydslRepositorySupport
import com.example.demo.persistence.dto.QSandboxDto
import com.example.demo.persistence.dto.SandboxCondition
import com.example.demo.persistence.dto.SandboxDto
import com.example.demo.persistence.entity.QSandboxEntity
import com.example.demo.persistence.entity.SandboxEntity
import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class SandboxRepositoryCustomImpl : CustomQuerydslRepositorySupport(SandboxEntity::class.java), SandboxRepositoryCustom {

    companion object {
        private val SANDBOX_ENTITY = QSandboxEntity.sandboxEntity
    }

    override fun findAllByCondition(condition: SandboxCondition, pageable: Pageable): Page<SandboxDto> {
        val query = getPaginationDefaultQuery()
        applyPaginationWhereClause(query, condition)
        val queryResults = querydsl.applyPagination(pageable, query).fetchResults()
        return PageImpl(queryResults.results, pageable, queryResults.total)
    }

    private fun getPaginationDefaultQuery(): JPQLQuery<SandboxDto> {
        val builder = JPAQueryFactory(entityManager)

        return builder
            .select(QSandboxDto(SANDBOX_ENTITY))
            .from(SANDBOX_ENTITY)
    }

    private fun applyPaginationWhereClause(query: JPQLQuery<SandboxDto>, condition: SandboxCondition) {
        condition.name?.let {
            query.where(SANDBOX_ENTITY.name.trim().containsIgnoreCase(it))
        }
        condition.age?.let {
            query.where(
                SANDBOX_ENTITY.age.goe(it.min),
                SANDBOX_ENTITY.age.loe(it.max)
            )
        }
        condition.category?.let {
            query.where(SANDBOX_ENTITY.category.eq(it))
        }
    }
}
