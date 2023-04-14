package com.example.demo.persistence.entity

import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

/**
 * 게시물 Entity.
 */
@Getter
@Setter
@Entity(name = "board")
class BoardEntity : BaseEntity() {

    /**
     * 일련번호.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    var boardId: Long = 0L

    /**
     * 제목.
     */
    @Column(name = "board_title", nullable = false)
    var boardTitle: String = ""

    /**
     * 사용여부.
     */
    @Column(name = "board_use_yn", nullable = false)
    var boardUseYn: Boolean = false

    /**
     * 등록일시.
     */
    @CreatedDate
    @Column(name = "create_date", updatable = false)
    var createDate: LocalDateTime = LocalDateTime.now()

    /**
     * 수정일시.
     */
    @LastModifiedDate
    @Column(name = "update_date")
    var updateDate: LocalDateTime = LocalDateTime.now()

    /**
     * 게시물 컨텐츠.
     */
    @OneToOne(
        mappedBy = "board",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE]
    )
    lateinit var contents: BoardContentsEntity

    /**
     * 게시물 첨부파일 목록.
     */
    @OneToMany(
        mappedBy = "board",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE],
        orphanRemoval = true
    )
    var attachments: MutableSet<BoardAttachmentEntity> = LinkedHashSet()


}