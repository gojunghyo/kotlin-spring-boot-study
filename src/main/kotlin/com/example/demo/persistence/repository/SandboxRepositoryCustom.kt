package com.example.demo.persistence.repository

import com.example.demo.persistence.dto.SandboxCondition
import com.example.demo.persistence.dto.SandboxDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SandboxRepositoryCustom {

    fun findAllByCondition(
        condition: SandboxCondition,
        pageable: Pageable
    ): Page<SandboxDto>
}