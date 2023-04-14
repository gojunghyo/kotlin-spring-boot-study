package com.example.demo.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): ResponseEntity<ResponseMessage> {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage("hello"));
    }
}