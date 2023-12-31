package me.kzv.mandalart.domain.mandalart

/**
 * 공개 범위
 */
enum class MandalartVisible {

    /** 비공개: 본인만 볼 수 있음 */
    PRIVATE,

    /** 공개 */
    PUBLIC,

    /** 링크가 있는 모든 사용자 */
    LINKONLY,

    /** 삭제 */
    DELETED,
}