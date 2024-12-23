package kr.tareun.hhplus.reservation.repository

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.reservation.model.Reservation
import kr.tareun.hhplus.user.model.User
import org.springframework.stereotype.Repository

@Repository
class ReservationCoreRepository: ReservationRepository {
    override fun getAllUsersByLectureId(lectureId: Long): List<User> {
        TODO("Not yet implemented")
    }

    override fun save(reservation: Reservation): Reservation {
        TODO("Not yet implemented")
    }

    override fun getLectureByLectureId(lectureId: Long): Lecture {
        TODO("Not yet implemented")
    }

    override fun reserveLecture(reservation: Reservation): Reservation {
        TODO("Not yet implemented")
    }

    override fun getAllLecturesByUserId(userId: Long): List<Lecture> {
        TODO("Not yet implemented")
    }

    override fun getAllReservationsByLectureId(lectureId: Long): List<Reservation> {
        TODO("Not yet implemented")
    }
}