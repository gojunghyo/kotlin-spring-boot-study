package com.example.demo.persistence.repository

import com.example.demo.persistence.entity.SandboxEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface SandboxRepository : JpaRepository<SandboxEntity, Long>, SandboxRepositoryCustom {
    fun findByIdAndDeletedFalse(id: Long): Optional<SandboxEntity>
}

