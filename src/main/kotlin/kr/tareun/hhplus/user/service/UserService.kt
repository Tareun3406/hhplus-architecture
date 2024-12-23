package kr.tareun.hhplus.user.service

import kr.tareun.hhplus.user.model.User
import kr.tareun.hhplus.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,) {
    fun getUsers(): List<User> {
        return userRepository.getAllUsers()
    }

    fun getUser(id: Long): User {
        return userRepository.getUserById(id)
    }

    fun addUser(user: User): User {
        return userRepository.addUser(user)
    }
}