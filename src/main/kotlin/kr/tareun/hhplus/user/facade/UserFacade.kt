package kr.tareun.hhplus.user.facade

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.reservation.service.ReservationService
import kr.tareun.hhplus.user.model.User
import kr.tareun.hhplus.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserFacade(
    private val userService: UserService,
    private val reservationService: ReservationService,
) {
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    fun getUser(id: Long): User {
        return userService.getUser(id)
    }

    fun addUser(user: User): User {
        return userService.addUser(user)
    }

    fun getReservedLectures(userId: Long): List<Lecture> {
        return reservationService.getLecturesByUserId(userId)
    }
}