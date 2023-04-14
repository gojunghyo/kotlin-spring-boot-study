package com.example.demo.persistence

import com.sun.istack.NotNull
import org.springframework.beans.factory.InitializingBean
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
class CustomQuerydslRepositorySupport(domainClass: Class<*>) : QuerydslRepositorySupport(domainClass), InitializingBean {

    override fun afterPropertiesSet() {
        Assert.notNull(querydsl, "The QueryDsl must not be null.")
    }

    @NotNull
    override fun getQuerydsl(): Querydsl {
        return requireNotNull(super.getQuerydsl())
    }

    @NotNull
    override fun getEntityManager(): EntityManager {
        return requireNotNull(super.getEntityManager())
    }

    @PersistenceContext
    override fun setEntityManager(@NotNull entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}