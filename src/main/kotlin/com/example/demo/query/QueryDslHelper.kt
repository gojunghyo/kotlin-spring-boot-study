package com.example.demo.query

import com.example.demo.persistence.dto.BoardDto
import com.querydsl.jpa.JPQLQuery
import java.util.function.Consumer

class QueryDslHelper private constructor() {
    init {
        throw UnsupportedOperationException()
    }

    class WhereCondition<V>(val value: V?) {
        fun then(consumer: Consumer<V>) {
            value?.let { consumer.accept(it) }
        }
    }

    companion object {
        fun <V> optionalWhen(value: V): WhereCondition<V> {
            return WhereCondition(value)
        }
    }
}