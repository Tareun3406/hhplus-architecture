package kr.tareun.hhplus.service.user

import kr.tareun.hhplus.domain.user.UserMapper
import kr.tareun.hhplus.domain.user.User
import kr.tareun.hhplus.domain.user.UserRequest
import kr.tareun.hhplus.domain.user.UserRepository
import kr.tareun.hhplus.domain.user.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@Suppress("NonAsciiCharacters")
class UserServiceUnitTest {

    @Mock
    lateinit var userRepository: UserRepository

    private var userMapper: UserMapper = UserMapper()
    private lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userService = UserService(userRepository, userMapper)
    }

    @Test
    fun `수강자를 추가할 수 있다`() {
        // given
        val request = UserRequest("수강자")
        val user = User(1L, "수강자")
        `when`(userRepository.save(any())).thenReturn(user)

        // when - then
        Assertions.assertEquals(user.id, userService.addUser(request).userId)
    }

    @Test
    fun `수강자를 조회할 수 있다`() {
        // given
        val user = User(1L, "수강자")
        `when`(userRepository.getUserById(user.id!!)).thenReturn(user)

        // when - then
        Assertions.assertEquals(user.id, userService.getUser(user.id!!).userId)
    }
}