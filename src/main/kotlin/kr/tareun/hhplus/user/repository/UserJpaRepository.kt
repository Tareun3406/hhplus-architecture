package kr.tareun.hhplus.user.repository

import kr.tareun.hhplus.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long> {
}