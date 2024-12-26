package kr.tareun.hhplus.infrastructure.reservation

import kr.tareun.hhplus.domain.reservation.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationJpaRepository: JpaRepository<Reservation, Long> {
    fun existsByUserIdAndLectureId(userId: Long, lectureId: Long): Boolean
    fun getAllByUserId(userId: Long): List<Reservation>
}