package me.kzv.mandalart.domain.user

import jakarta.persistence.*
import me.kzv.mandalart.support.jpa.BaseEntity
import me.kzv.mandalart.domain.user.UserStatus.*
import java.time.OffsetDateTime

/**
 * 유저
 * - 소셜로그인을 통해 가입한다.
 */
@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(name = "uq_user_email", columnNames = ["email"])
    ]
)
class User(

    /** 이메일 */
    @Column(length = 50, nullable = false)
    val email: String,

    /** 닉네임 */
    @Column(length = 50, nullable = false)
    val nickName: String,

    /** 프로필 이미지 경로 */
    var profileImageUrl: String,

    /** 상태 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    var status: UserStatus = ACTIVE,

    /** 활동 정지 사유 */
    var stoppedReason: String? = null,

    /** 활동 정지 종료일 */
    var stopEndDate: OffsetDateTime? = null,
) : BaseEntity() {

    fun inactivate() {
        check(status == ACTIVE)

        this.status = INACTIVE
    }

    fun stop(reason: String) {
        this.status = STOPPED
        this.stoppedReason = reason
    }
}