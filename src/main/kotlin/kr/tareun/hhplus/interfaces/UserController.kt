package kr.tareun.hhplus.interfaces

import kr.tareun.hhplus.domain.user.UserRequest
import kr.tareun.hhplus.domain.user.UserResponse
import kr.tareun.hhplus.domain.user.UserService
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
@RestController
class UserController(private val userService: UserService) {

    // 사용자 추가
    @PostMapping
    fun addUser(@RequestBody user: UserRequest): UserResponse = userService.addUser(user)
}
