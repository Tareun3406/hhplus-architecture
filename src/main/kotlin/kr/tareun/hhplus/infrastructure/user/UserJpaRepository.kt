package kr.tareun.hhplus.infrastructure.user

import kr.tareun.hhplus.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long>