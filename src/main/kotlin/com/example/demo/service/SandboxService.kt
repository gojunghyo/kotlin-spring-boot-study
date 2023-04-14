package com.example.demo.service

import com.example.demo.persistence.dto.SandboxCondition
import com.example.demo.persistence.dto.SandboxDto
import com.example.demo.persistence.repository.SandboxRepository
import com.example.demo.service.dto.SandboxItem
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional
class SandboxService(private val sandboxRepository: SandboxRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun getPaginatedSandbox(condition: SandboxCondition, pageable: Pageable): Page<SandboxItem> {
        return sandboxRepository.findAllByCondition(condition, pageable).map(SandboxItem::of)
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun getSandbox(id: Long): SandboxDto {
        val storedEntity = sandboxRepository.findByIdAndDeletedFalse(id)
            .orElseThrow { EntityNotFoundException("[$id]는 존재하지 않거나, 삭제된 상태입니다.") }

        return SandboxDto(storedEntity)
    }
}
