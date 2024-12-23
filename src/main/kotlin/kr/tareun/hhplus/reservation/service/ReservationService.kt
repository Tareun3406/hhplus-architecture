package kr.tareun.hhplus.reservation.service

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.lecture.repository.LectureRepository
import kr.tareun.hhplus.reservation.model.Reservation
import kr.tareun.hhplus.reservation.repository.ReservationRepository
import kr.tareun.hhplus.user.model.User
import kr.tareun.hhplus.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val lectureRepository: LectureRepository,
    private val userRepository: UserRepository
) {
    fun reserveLecture(lectureId: Long, userId: Long): Reservation {
        val lecture = lectureRepository.getLectureWithReservationsById(lectureId)
        val user = userRepository.getUserById(userId)
        val reservation = Reservation(null, user, lecture)

        lecture.addReservation(reservation) // 정책 확인용
        return reservationRepository.save(reservation)
    }

    fun getUsersByLectureId(lectureId: Long): List<User> {
        return reservationRepository.getAllUsersByLectureId(lectureId)
    }

    fun getLecturesByUserId(userId: Long): List<Lecture> {
        return reservationRepository.getAllLecturesByUserId(userId)
    }
}