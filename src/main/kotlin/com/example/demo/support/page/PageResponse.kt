package com.example.demo.support.page

import io.swagger.v3.oas.annotations.media.Schema
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.data.domain.Page


@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페이징 응답 정보")
data class PageResponse<T>(
    @Schema(description = "페이지 당 게시물 수")
    var pageSize: Int,

    @Schema(description = "현재 페이지 번호")
    var pageNumber: Int,

    @Schema(description = "존재하는 총 페이지 수")
    var totalPageNumber: Int,

    @Schema(description = "존재하는 총 데이터 수")
    var totalSize: Long,

    @Schema(description = "현재 페이지 데이터 목록")
    var list: List<T>? = null
){
    companion object {
        fun <T> convert(page: Page<T>): PageResponse<T> {
//            val response = PageResponse<T>()
//            response.pageNumber = page.number + 1
//            response.pageSize = page.size
//            response.totalPageNumber = page.totalPages
//            response.totalSize = page.totalElements
//            response.list = page.content
//            return response
            return PageResponse(
                pageNumber = page.number + 1,
                pageSize = page.size,
                totalPageNumber = page.totalPages,
                totalSize = page.totalElements,
                list = page.content,
            )
        }



    }

}