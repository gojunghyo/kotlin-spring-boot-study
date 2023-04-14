package com.example.demo.controller

import com.example.demo.persistence.dto.BoardCondition
import com.example.demo.persistence.dto.BoardDto
import com.example.demo.service.BoardService
import com.example.demo.service.dto.BoardItem
import com.example.demo.service.dto.BoardSource
import com.example.demo.support.dto.ApiResponse
import com.example.demo.support.dto.ApiResponseGenerator
import com.example.demo.support.dto.ApiResponseGenerator.Companion.success
import com.example.demo.support.page.PageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import kotlin.streams.toList

@Tag(name = "BOARD", description = "게시물 관리 샘플 API")
@Slf4j
@Validated
@RestController
@RequestMapping("/v1/board")
class BoardController(private val boardService: BoardService) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Operation(summary = "페이징 처리된 게시물 목록 조회", description = "페이징 처리된 게시물 목록을 조회할 수 있습니다.")
    @GetMapping
    fun getPaginatedItem(@Valid condition: BoardCondition,
                         @ParameterObject @PageableDefault(
                             size = 25,
                             sort = ["boardId"],
                             direction = Sort.Direction.ASC
                         ) pageable: Pageable): ApiResponse<PageResponse<BoardItem>> {
        var paginatedResult: Page<BoardItem> = boardService
            .getPaginatedBoard(condition, pageable)
        log.info("paginatedResult = {}", paginatedResult.get().toList().get(0))
        return ApiResponseGenerator().success(PageResponse.convert(paginatedResult))
    }

    @Operation(summary = "특정 게시물 조회", description = "특정 게시물을 조회할수 있습니다.")
    @GetMapping("/{boardId}")
    fun getSandbox(
        @Parameter(description = "게시물 ID", required = true)
        @PathVariable @Min(1)  boardId: Long
    ): ApiResponse<BoardDto> {
        return success(boardService.getBoard(boardId))
    }

    @Operation(summary = "게시물 생성", description = "특정 게시물을 생성할 수 있습니다.")
    @PostMapping
    fun postBoard(@RequestBody @Valid source: BoardSource): ApiResponse<BoardDto> {
        return success(boardService.insertBoard(source))
    }

    @Operation(summary = "특정 게시물 수정", description = "특정 게시물을 수정할 수 있습니다.")
    @PutMapping("/{boardId}")
    fun putBoard(
        @Parameter(description = "게시물 ID", required = true)
        @PathVariable @Min(1) boardId: Long,
        @RequestBody @Valid source: BoardSource
    ): ApiResponse<BoardDto> {
        return success(boardService.updateBoard(boardId, source))
    }

    @Operation(summary = "특정 게시물 삭제")
    @DeleteMapping("/{boardId}")
    fun deleteBoard(
        @Parameter(description = "게시물 ID", required = true)
        @PathVariable @Min(1) boardId: Long
    ): ApiResponse<BoardDto> {
        return success(boardService.deleteBoard(boardId))
    }


}

