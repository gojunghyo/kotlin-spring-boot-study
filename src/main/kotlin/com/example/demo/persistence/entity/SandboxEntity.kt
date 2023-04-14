package com.example.demo.persistence.entity

import com.example.demo.persistence.code.SandboxCategory
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "sandbox")
class SandboxEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String = "",

    @Column(name = "age", nullable = false)
    var age: Int = 0,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    var category: SandboxCategory = SandboxCategory.NORMAL,

    @Column(name = "deleted", nullable = false)
    var deleted: Boolean = false,

    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false)
    var createDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    var updateDate: LocalDateTime = LocalDateTime.now()
)
