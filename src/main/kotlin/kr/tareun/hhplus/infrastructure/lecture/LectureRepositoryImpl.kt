package kr.tareun.hhplus.infrastructure.lecture

import kr.tareun.hhplus.domain.lecture.Lecture
import kr.tareun.hhplus.domain.lecture.LectureRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Repository
class LectureRepositoryImpl(private val jpaRepository: LectureJpaRepository): LectureRepository {

    override fun getLecture(lectureId: Long): Lecture = jpaRepository.getReferenceById(lectureId)
    override fun getLectureForUpdate(lectureId: Long): Lecture = jpaRepository.getByIdWithWriteLock(lectureId)

    override fun getAllLecturesByDate(date: LocalDate): List<Lecture> {
        val startDateTime = LocalDateTime.of(date, LocalTime.MIN)
        val endDateTime = LocalDateTime.of(date, LocalTime.MAX)
        return jpaRepository.findByStartTimeBetween(startDateTime, endDateTime)
    }

    override fun save(lecture: Lecture): Lecture = jpaRepository.save(lecture)

}