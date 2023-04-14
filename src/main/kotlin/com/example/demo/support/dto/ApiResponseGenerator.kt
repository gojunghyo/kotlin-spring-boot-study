package com.example.demo.support.dto

import com.example.demo.service.dto.BoardItem
import com.example.demo.support.code.ErrorCode
import com.example.demo.support.page.PageResponse

class ApiResponseGenerator() {
    fun success(convert: PageResponse<BoardItem>): ApiResponse<PageResponse<BoardItem>> {
        return ApiResponse(ErrorCode.SUCCESS, null, convert)
    }


    companion object {
        private val RESULT_SUCCESS: ApiResponse<Void> = ApiResponse(ErrorCode.SUCCESS)
        private val RESULT_ERROR: ApiResponse<Void> = ApiResponse(ErrorCode.UNKNOWN_ERROR)

        @JvmStatic
        fun success(): ApiResponse<Void> {
            return RESULT_SUCCESS
        }

        @JvmStatic
        fun <D> success(data: D): ApiResponse<D> {
            return ApiResponse(ErrorCode.SUCCESS, null, data)
        }

        @JvmStatic
        fun fail(): ApiResponse<Void> {
            return RESULT_ERROR
        }

        @JvmStatic
        fun fail(code: ErrorCode): ApiResponse<Void> {
            return ApiResponse(code)
        }

        @JvmStatic
        fun <D> fail(code: ErrorCode, data: D): ApiResponse<D> {
            return ApiResponse(code, data)
        }

//        @JvmStatic
//        fun fail(code: ErrorCode, msg: String): ApiResponse<Void> {
//            return ApiResponse(code, msg, null)
//        }

        @JvmStatic
        fun fail(code: ErrorCode, msg: String, errorCode: String): ApiResponse<String> {
            return ApiResponse(code, msg, errorCode)
        }
    }
}
