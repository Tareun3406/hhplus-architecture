package kr.tareun.hhplus.domain.lecture

import java.time.LocalDate

interface LectureRepository {
    fun save(lecture: Lecture): Lecture
    fun getLecture(lectureId: Long): Lecture
    fun getAllLecturesByDate(date: LocalDate): List<Lecture>
}