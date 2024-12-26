package kr.tareun.hhplus.domain.user

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class UserService(private val userRepository: UserRepository, private val userMapper: UserMapper) {
    // 유저 정보 조회
    fun getUser(id: Long): UserResponse = userMapper.toUserResponse(userRepository.getUserById(id))

    // 유저 추가
    fun addUser(user: UserRequest): UserResponse = userMapper.toUserResponse(
        userRepository.save(userMapper.toUserEntity(user))
    )
}