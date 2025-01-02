package kr.tareun.hhplus.domain.user

interface UserRepository {
    fun getUserById(id: Long): User
    fun save(user: User): User
}