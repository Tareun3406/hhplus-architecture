package kr.tareun.hhplus.lecture.facade

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.lecture.service.LectureService
import kr.tareun.hhplus.reservation.model.Reservation
import kr.tareun.hhplus.reservation.service.ReservationService
import kr.tareun.hhplus.user.model.User
import org.springframework.stereotype.Service

@Service
class LectureFacade(
    val lectureService: LectureService,
    val reservationService: ReservationService
) {
    fun getAllLectures(): List<Lecture> {
        return lectureService.getAllLectures()
    }

    fun getLecture(lectureId: Long): Lecture {
        return lectureService.getLecture(lectureId)
    }

    fun addLecture(lecture: Lecture): Lecture {
        return lectureService.addLecture(lecture)
    }

    fun getReservedUsers(lectureId: Long): List<User> {
        return reservationService.getUsersByLectureId(lectureId)
    }

    fun reserveLecture(lectureId: Long, userId: Long): Reservation {
        return reservationService.reserveLecture(lectureId, userId)
    }
}