package kr.tareun.hhplus.infrastructure.user

import kr.tareun.hhplus.domain.user.User
import kr.tareun.hhplus.domain.user.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val jpaRepository: UserJpaRepository): UserRepository {

    // 유저 정보 조회
    override fun getUserById(id: Long): User = jpaRepository.getReferenceById(id)

    // 유저 정보 저장
    override fun save(user: User): User = jpaRepository.save(user)
}