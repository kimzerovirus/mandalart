package me.kzv.mandalart.domain.mandalart

import me.kzv.mandalart.domain.user.User
import me.kzv.mandalart.domain.user.createUser
import org.junit.jupiter.api.Test

class MandalartTest {

    @Test
    fun `랜덤링크 생성 테스트`() {
        val mandalart = createMandalart()
        mandalart.visibleLinkOnly()

        println("===link===")
        println(mandalart.link)
    }

}

fun createMandalart(
    user: User = createUser(), finalGoal: String = "1조 부자"
): Mandalart {
    return Mandalart(
        user = user, finalGoal = finalGoal
    )
}