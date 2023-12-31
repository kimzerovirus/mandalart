package me.kzv.mandalart.domain.user

import org.junit.jupiter.api.Assertions.*

class UserTest {

}

fun createUser(
    email: String = "kimzerovirus@gmail.com",
    nickname: String = "kimzerovirus",
    profileImageUrl: String = ""
): User {
    return User(email, nickname, profileImageUrl)
}