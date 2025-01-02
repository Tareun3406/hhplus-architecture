package kr.tareun.hhplus.domain.user

import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toUserResponse(user: User): UserResponse = UserResponse(userId = user.id!!, userName = user.name)
    fun toUserEntity(userRequest: UserRequest): User = User(id = null, name = userRequest.userName)
}