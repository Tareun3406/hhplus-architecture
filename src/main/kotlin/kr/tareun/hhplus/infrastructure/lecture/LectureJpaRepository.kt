package kr.tareun.hhplus.infrastructure.lecture

import kr.tareun.hhplus.domain.lecture.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface LectureJpaRepository: JpaRepository<Lecture, Long> {
    fun findByStartTimeBetween(startDay: LocalDateTime, endDay: LocalDateTime): List<Lecture>
}