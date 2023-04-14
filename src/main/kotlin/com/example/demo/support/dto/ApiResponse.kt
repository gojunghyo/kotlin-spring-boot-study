package com.example.demo.support.dto

import com.example.demo.support.code.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "API 응답 정보")
class ApiResponse<T> {
    @Schema(description = "응답 코드")
    var code: String?

    @Schema(description = "응답 메시지")
    var message: String

    @Schema(description = "응답 데이터")
    var data: T

    constructor(errorCode: ErrorCode) : this(errorCode,null,null)

    constructor(errorCode: ErrorCode, data: T) : this(errorCode, null, data)

    constructor(errorCode: ErrorCode, responseMessage: String?, data: Any?) {
        this.code = errorCode.code
        this.message = responseMessage ?: errorCode.defaultMessage
        this.data = data as T
    }

}
