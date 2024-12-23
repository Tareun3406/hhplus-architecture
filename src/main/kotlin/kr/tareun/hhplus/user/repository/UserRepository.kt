package kr.tareun.hhplus.user.repository

import kr.tareun.hhplus.user.model.User

interface UserRepository {
    fun getAllUsers(): List<User>
    fun getUserById(id: Long): User
    fun addUser(user: User): User
}