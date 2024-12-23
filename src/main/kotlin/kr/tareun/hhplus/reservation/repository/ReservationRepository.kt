package kr.tareun.hhplus.reservation.repository

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.reservation.model.Reservation
import kr.tareun.hhplus.user.model.User

interface ReservationRepository {
    fun save(reservation: Reservation): Reservation
    fun getLectureByLectureId(lectureId: Long): Lecture
    fun reserveLecture(reservation: Reservation): Reservation
    fun getAllUsersByLectureId(lectureId: Long): List<User>
    fun getAllLecturesByUserId(userId: Long): List<Lecture>
    fun getAllReservationsByLectureId(lectureId: Long): List<Reservation>
}