package kr.tareun.hhplus.user.controller

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.user.facade.UserFacade
import kr.tareun.hhplus.user.model.User
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
@RestController
class UserController(private val userFacade: UserFacade) {

    @GetMapping()
    fun getUsers(): List<User> {
        return userFacade.getUsers();
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable("userId") userId: Long): User {
        return userFacade.getUser(userId)
    }

    @PostMapping
    fun addUser(@RequestBody user: User): User {
        return userFacade.addUser(user)
    }

    @GetMapping("/{userId}/reservations")
    fun getReservations(@PathVariable("userId") userId: Long): List<Lecture> {
        return userFacade.getReservedLectures(userId)
    }
}