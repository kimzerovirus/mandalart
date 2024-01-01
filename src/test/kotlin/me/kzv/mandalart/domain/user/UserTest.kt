package me.kzv.mandalart.domain.user

import me.kzv.mandalart.security.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class UserTest {

    @Test
    fun `유저 생성`() {
        val email = "kzv@gmail.com"
        val nickname = "테스트유저"
        val socialPlatform = SocialPlatform.GOOGLE
        val profileImageUrl = "http://www.image.com/xyz"

        val user = User(email, nickname, profileImageUrl, socialPlatform = socialPlatform)

        assertThat(user.email).isEqualTo(email)
        assertThat(user.nickname).isEqualTo(nickname)
        assertThat(user.socialPlatform).isEqualTo(socialPlatform)
        assertThat(user.profileImageUrl).isEqualTo(profileImageUrl)
        assertThat(user.roles).contains(UserRole.USER)
        assertThat(user.roles.size).isEqualTo(1)
    }

    @Test
    fun `유저 비활성화`() {
        val user = createUser()

        user.inactivate()

        assertThat(user.status).isEqualTo(UserStatus.INACTIVE)
    }

    @Test
    fun `어드민 권한 부여`() {
        val user = createUser()

        user.addAdminRole()

        assertThat(user.roles).contains(UserRole.ADMIN)
    }

    @Test
    fun `어드민 권한 회수`() {
        val user = createUser().apply {
            this.roles.add(UserRole.ADMIN)
        }

        user.removeAdminRole()

        assertThat(user.roles).doesNotContain(UserRole.ADMIN)
    }

    @Test
    fun `유저 활동 정지`() {
        val user = createUser()
        val stoppedReason = "정지 사유는 다국어 지원을 위해 추후 enum으로 대체해도 될듯하다."
        val stopEndDate = OffsetDateTime.now()

        user.stop(stoppedReason, stopEndDate)

        assertThat(user.status).isEqualTo(UserStatus.STOPPED)
        assertThat(user.stoppedReason).isEqualTo(stoppedReason)
        assertThat(stopEndDate).isEqualTo(stopEndDate)
    }
    
    @Test
    fun `닉네임 변경`() {
        val oldNickname = "kzv"
        val user = createUser().apply {
            this.nickname = oldNickname
        }
        val nickname = "mela"

        user.changeNickname(nickname)

        assertThat(user.nickname).isNotEqualTo(oldNickname)
        assertThat(user.nickname).isEqualTo(nickname)
    }
}

fun createUser(
    email: String = "kimzerovirus@gmail.com",
    nickname: String = "kimzerovirus",
    profileImageUrl: String = "",
    socialPlatform: SocialPlatform = SocialPlatform.GOOGLE
): User {
    return User(email, nickname, profileImageUrl, socialPlatform = socialPlatform)
}