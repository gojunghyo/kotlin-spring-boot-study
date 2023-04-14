package com.example.demo.`5`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MyService {
    fun perforAction(): String  = "FOO"

}

class MyTest{
    private lateinit var myService : MyService

    @BeforeEach
    fun setUp(){
        myService = MyService()
    }

    @Test
    fun  testAction() {
        Assertions.assertEquals("FOO", myService!!.perforAction())
    }
}