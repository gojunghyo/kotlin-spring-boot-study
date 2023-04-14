package com.example.demo.persistence.entity

import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.*

/**
 * 게시물 컨텐츠 Entity.
 */
@Entity(name = "board_contents")
class BoardContentsEntity{

    /**
     * 게시물 번호.
     */
    @Id
    @Column(name = "board_id", nullable = false)
    var boardId: Long = 0

    /**
     * 게시물 컨텐츠 내용.
     */
    @Column(name = "board_contents", nullable = false, columnDefinition = "TEXT")
    lateinit var boardContents: String

    /**
     * 게시물 정보.
     */
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    lateinit var board: BoardEntity

    constructor()
}