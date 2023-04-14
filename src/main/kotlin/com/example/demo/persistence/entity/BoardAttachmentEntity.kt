package com.example.demo.persistence.entity
import lombok.EqualsAndHashCode
import javax.persistence.*

@Entity(name = "board_attachment")
class BoardAttachmentEntity(

    /**
     * 게시물 첨부파일 번호.
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_attachment_id", nullable = false)
    var boardAttachmentId: Long,

    /**
     * 게시물 일련번호.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    var board: BoardEntity,

    /**
     * 첨부파일경로.
     */
    @EqualsAndHashCode.Include
    @Column(name = "board_attachment_path", nullable = false)
    var boardAttachmentPath: String,

    /**
     * 첨부파일명.
     */
    @get:EqualsAndHashCode.Include
    @get:Column(name = "board_attachment_name", nullable = false)
    var boardAttachmentName: String

) : BaseEntity() {

    constructor(board: BoardEntity, boardAttachmentPath: String, boardAttachmentName: String)
            : this(0, board, boardAttachmentPath, boardAttachmentName)


}