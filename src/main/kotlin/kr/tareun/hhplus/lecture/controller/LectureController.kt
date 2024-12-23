package kr.tareun.hhplus.lecture.controller

import kr.tareun.hhplus.lecture.facade.LectureFacade
import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.reservation.model.Reservation
import kr.tareun.hhplus.user.model.User
import org.springframework.web.bind.annotation.*

@RequestMapping("/lectures")
@RestController
class LectureController(private val lectureFacade: LectureFacade) {

    @GetMapping
    fun getAllLectures(): List<Lecture> {
        return lectureFacade.getAllLectures()
    }

    @GetMapping("/{lectureId}")
    fun getLecture(@PathVariable("lectureId") lectureId: Long): Lecture {
        return lectureFacade.getLecture(lectureId)
    }

    @PostMapping
    fun addLecture(@RequestBody lecture: Lecture): Lecture {
        return lectureFacade.addLecture(lecture)
    }

    @PostMapping("/{lectureId}/reservations")
    fun reserveLecture(@PathVariable lectureId: Long, @RequestBody userId: Long): Reservation {
        return lectureFacade.reserveLecture(lectureId, userId)
    }

    @GetMapping("/{lectureId}/reservations")
    fun getUserReservation(@PathVariable lectureId: Long): List<User> {
        return lectureFacade.getReservedUsers(lectureId)
    }
}