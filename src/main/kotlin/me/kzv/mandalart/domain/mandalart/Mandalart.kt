package me.kzv.mandalart.domain.mandalart

import jakarta.persistence.*
import me.kzv.mandalart.domain.goal.Goal
import me.kzv.mandalart.domain.mandalart.MandalartVisible.*
import me.kzv.mandalart.domain.user.User
import me.kzv.mandalart.support.jpa.BaseEntity

/**
 * 만다라트
 *
 * - 최종 목표 달성을 위한 8개의 목표를 설정할 수 있다.
 */
@Entity
class Mandalart(

    /** 만다라트 소유자 */
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    /** 목표 */
    @OneToMany(mappedBy = "mandalart")
    var goals: MutableList<Goal> = mutableListOf(),

    /** 최종 목표 */
    @Column(nullable = false)
    var finalGoal: String,

    /**
     * 1. 내부 집계용 조회 수로 할지(공개된 만다라트 중 지금 핫한 만다라트 이런식으로 보여주기)
     * 2. 깃허브 히트 처럼 할지 아직 결정 못함
     * */
    var hits: Int = 0,

    /** 링크 경로 */
    @Column(length = 50)
    var link: String? = null,

    /** 공개 범위 */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    var visible: MandalartVisible = PRIVATE,
) : BaseEntity() {

    fun isPublic(): Boolean {
        return this.visible == PUBLIC
    }

    fun isLinkOnly(): Boolean {
        return this.visible == LINKONLY && (this.link != "" || this.link != null)
    }

    fun shrink() {
        this.goals.forEach { it.shrink() }
    }

    private fun makeLink(): String {
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        val randomLink = List(36){ charset.random()}

        return randomLink.joinToString("")
    }

    fun visibleLinkOnly() {
        this.link = makeLink()
        this.visible = LINKONLY
    }

    fun visiblePrivate(){
        this.visible = PRIVATE
    }

    fun visiblePublic(){
        this.visible = PUBLIC
    }

    fun delete(){
        this.visible = DELETED
    }
}