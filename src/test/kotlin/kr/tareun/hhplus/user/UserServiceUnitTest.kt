package kr.tareun.hhplus.user

import kr.tareun.hhplus.user.model.User
import kr.tareun.hhplus.user.repository.UserRepository
import kr.tareun.hhplus.user.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@Suppress("NonAsciiCharacters")
class UserServiceUnitTest {

    @Mock
    lateinit var userRepository: UserRepository

    @InjectMocks
    lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `수강자를 추가할 수 있다`() {
        // given
        val user = User(1L, "수강자")
        `when`(userRepository.addUser(user)).thenReturn(user)

        // when - then
        Assertions.assertEquals(user.id, userService.addUser(user).id)
    }

    @Test
    fun `수강자를 조회할 수 있다`() {
        // given
        val user = User(1L, "수강자")
        `when`(userRepository.getUserById(user.id!!)).thenReturn(user)

        // when - then
        Assertions.assertEquals(user.id, userService.getUser(user.id!!).id)
    }

    @Test
    fun `수강자 목록을 조회할 수 있다`() {
        val userList = listOf(User(1L, "수강자1"), User(2L, "수강자2"))
        `when`(userRepository.getAllUsers()).thenReturn(userList)

        // when
        val resultList = userService.getUsers()

        // then
        Assertions.assertEquals(userList[0].id, resultList[0].id)
        Assertions.assertEquals(userList[1].id, resultList[1].id)
    }
}