package kr.tareun.hhplus.lecture.repository

import kr.tareun.hhplus.lecture.model.Lecture

interface LectureRepository {
    fun save(lecture: Lecture): Lecture
    fun getAllLectures(): List<Lecture>
    fun getLecture(lectureId: Long): Lecture
    fun getLectureWithReservationsById(lectureId: Long): Lecture
}