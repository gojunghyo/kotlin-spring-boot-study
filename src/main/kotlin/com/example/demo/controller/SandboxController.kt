package com.example.demo.controller

import com.example.demo.persistence.dto.SandboxCondition
import com.example.demo.persistence.dto.SandboxDto
import com.example.demo.service.SandboxService
import com.example.demo.service.dto.SandboxItem
import com.example.demo.support.dto.ApiResponse
import com.example.demo.support.dto.ApiResponseGenerator
import com.example.demo.support.page.PageResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min

@Tag(name = "SANDBOX", description = "샘플 관리 API")
@RestController
@RequestMapping("/v1/sandbox")
@Validated
@RequiredArgsConstructor
class SandboxController(private val sandboxService: SandboxService) {

    @Operation(
        summary = "페이징 처리된 게시물 목록 조회",
        description = "페이징 처리된 게시물 목록을 조회할 수 있습니다."
    )
    @GetMapping
    fun getPaginatedItem(
        @Valid condition: SandboxCondition,
        @ParameterObject @PageableDefault(size = 25, sort = ["name"], direction = Sort.Direction.ASC) pageable: Pageable
    ): ApiResponse<PageResponse<SandboxItem>> {
        val paginatedResult = sandboxService.getPaginatedSandbox(condition, pageable)
        return ApiResponseGenerator.success(PageResponse.convert(paginatedResult))
    }

    @Operation(summary = "특정 게시물 조회", description = "특정 게시물을 조회할 수 있습니다.")
    @GetMapping("/{id}")
    fun getSandbox(
        @Parameter(description = "게시물 ID", required = true)
        @PathVariable @Min(1) id: Long
    ): ApiResponse<SandboxDto> {
        return ApiResponseGenerator.success(sandboxService.getSandbox(id))
    }
}
