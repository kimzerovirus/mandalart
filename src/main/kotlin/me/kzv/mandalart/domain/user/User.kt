package me.kzv.mandalart.domain.user

import jakarta.persistence.*
import me.kzv.mandalart.support.jpa.BaseEntity
import me.kzv.mandalart.domain.user.UserStatus.*
import me.kzv.mandalart.security.UserRole
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
    var nickname: String,

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

    /** 권한 */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY) // default lazy 이지만 명시
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    var roles: MutableList<UserRole> = mutableListOf(UserRole.USER),

    /** 연동 소셜 플랫폼 */
    @Enumerated(EnumType.STRING)
    var socialPlatform: SocialPlatform
) : BaseEntity() {

    fun addAdminRole() {
        this.roles.add(UserRole.ADMIN)
    }

    fun removeAdminRole() {
        this.roles.remove(UserRole.ADMIN)
    }

    fun inactivate() {
        check(status == ACTIVE)

        this.status = INACTIVE
    }

    // TODO 정지 기간 정의 필요
    fun stop(reason: String, stopEndDate: OffsetDateTime?) {
        this.status = STOPPED
        this.stoppedReason = reason
        this.stopEndDate = stopEndDate
    }

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }
}