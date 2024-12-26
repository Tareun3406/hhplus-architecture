package kr.tareun.hhplus.infrastructure.lecture

import jakarta.persistence.LockModeType
import kr.tareun.hhplus.domain.lecture.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface LectureJpaRepository: JpaRepository<Lecture, Long> {
    fun findByStartTimeBetween(startDay: LocalDateTime, endDay: LocalDateTime): List<Lecture>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select lecture from Lecture lecture where lecture.id = :id")
    fun getByIdWithWriteLock(id: Long): Lecture
}