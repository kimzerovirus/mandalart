package me.kzv.mandalart.domain.user

enum class UserStatus {
    /** 정상 활동 중인 유저 */
    ACTIVE,

    /** 탈퇴된 유저 */
    INACTIVE,

    /** 활동 정지된 유저 */
    STOPPED
}