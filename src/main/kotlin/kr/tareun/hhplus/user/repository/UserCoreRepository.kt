package kr.tareun.hhplus.user.repository

import kr.tareun.hhplus.user.model.User
import org.springframework.stereotype.Repository

@Repository
class UserCoreRepository(private val jpaRepository: UserJpaRepository): UserRepository {
    override fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: Long): User {
        TODO("Not yet implemented")
    }

    override fun addUser(user: User): User {
        TODO("Not yet implemented")
    }
}