package me.kzv.mandalart.domain.goal

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import me.kzv.mandalart.domain.mandalart.Mandalart
import me.kzv.mandalart.support.jpa.BaseEntity

/**
 * 목표
 *
 * - 목표 달성을 위해 3개 또는 8개를 할일을 설정할 수 있다.
 */
@Entity
class Goal(

    @ManyToOne
    val mandalart: Mandalart,

    var title: String = "",

    /** 할일 1 ~ 8
     * 지속적으로 수정이 일어날 것이므로 @ElementCollection과 같이
     * 1:M 관계로 이어나가는 것보다 그냥 필드로 넣는 게 나을 듯?
     * */
    var todo1: String = "",
    var todo2: String = "",
    var todo3: String = "",
    var todo4: String = "",
    var todo5: String = "",
    var todo6: String = "",
    var todo7: String = "",
    var todo8: String = "",
) : BaseEntity() {

    fun shrink() {
        val newTodoList = mutableListOf<String>()
        val todoList = listOf(todo1, todo2, todo3, todo4,todo5, todo6, todo7, todo8)

        // 빈칸이 있으면 앞으로 당겨서 3개의 todo로 맞춰 준다.
        run breaker@{
            todoList.forEach {
                if(newTodoList.size >= 3) {
                    return@breaker
                }

                if (it != "") {
                    newTodoList.add(it)
                }
            }
        }

        this.todo4 = ""
        this.todo5 = ""
        this.todo6 = ""
        this.todo7 = ""
        this.todo8 = ""
    }
}