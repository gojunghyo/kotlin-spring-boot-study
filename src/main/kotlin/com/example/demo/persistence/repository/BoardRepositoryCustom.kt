package com.example.demo.persistence.repository
import com.example.demo.persistence.dto.BoardCondition
import com.example.demo.persistence.dto.BoardDto
import com.example.demo.service.dto.BoardItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardRepositoryCustom {

    fun findAllByCondition(
        condition: BoardCondition,
        pageable: Pageable
    ): Page<BoardDto>

}