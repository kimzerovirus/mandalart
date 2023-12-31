package me.kzv.mandalart.domain.cheer

import jakarta.persistence.*
import me.kzv.mandalart.domain.mandalart.Mandalart
import me.kzv.mandalart.domain.user.User
import me.kzv.mandalart.support.jpa.BaseEntity

/**
 * 응원
 *
 * - 하나의 만다라트에 대한 응원 댓글이다.
 */
@Entity
class Cheer(
    @ManyToOne(fetch = FetchType.LAZY)
    var mandalart: Mandalart,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User,

    /** 응원 댓글 */
    var text: String,

    /** 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    var status: CheerStatus = CheerStatus.CREATED,
) : BaseEntity() {

}