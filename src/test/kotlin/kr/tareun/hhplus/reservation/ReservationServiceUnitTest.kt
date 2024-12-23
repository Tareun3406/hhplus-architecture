package kr.tareun.hhplus.reservation

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.reservation.model.Reservation
import kr.tareun.hhplus.reservation.repository.ReservationRepository
import kr.tareun.hhplus.reservation.service.ReservationService
import kr.tareun.hhplus.user.model.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@Suppress("NonAsciiCharacters")
class ReservationServiceUnitTest {

    @Mock
    lateinit var reservationRepository: ReservationRepository

    @InjectMocks
    lateinit var reservationService: ReservationService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `수강자가 특강을 신청할 수 있다`() {
        // given
        val user = User(1, "수강자")
        val lecture = Lecture(1, "특강1", LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        val reservation = Reservation(1, user, lecture)
        `when`(reservationRepository.reserveLecture(reservation)).thenReturn(reservation)

        // when - then
        Assertions.assertEquals(reservation.id, reservationService.reserveLecture(user.id!!, reservation.id!!).id)
    }

    @Test
    fun `수강자 ID로 신청한 특강 목록을 조회할 수 있다`() {
        // given
        val userId = 1L
        val reservationList = listOf(
            Lecture(1L, "특강 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1)),
            Lecture(2L, "특강 2", LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))
        )
        `when`(reservationRepository.getAllLecturesByUserId(userId)).thenReturn(reservationList)

        // when
        val result = reservationService.getLecturesByUserId(userId)

        // then
        Assertions.assertEquals(reservationList[0].id, result[0].id)
        Assertions.assertEquals(reservationList[1].id, result[1].id)
    }

    @Test
    fun `특강 ID로 신청한 수강자 목록을 조회할 수 있다`() {
        // given
        val lectureId = 1L
        val userList = listOf(User(1L, "수강자1"), User(2L, "수강자2"))
        `when`(reservationService.getUsersByLectureId(lectureId)).thenReturn(userList)

        // when
        val result = reservationService.getUsersByLectureId(lectureId)

        // then
        Assertions.assertEquals(userList[0].id, result[0].id)
        Assertions.assertEquals(userList[1].id, result[1].id)
    }

    @Test
    fun `이미 신청한 특강을 추가로 신청할 수 없다`() {
        // given
        val user = User(1L, "수강자")
        val reservingLecture = Lecture(1L, "특강1", LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        `when`(reservationRepository.getAllLecturesByUserId(1L)).thenReturn(listOf(reservingLecture))

        // when - then
        Assertions.assertThrows(Exception::class.java) {
            reservationService.reserveLecture(user.id!!, reservingLecture.id!!)
        }
    }

    @Test
    fun `이미 30명이 신청한 특강은 신청할 수 없다`() {
        // given
        val lectureId = 1L
        val lecture = Lecture(1, "특강1", LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        val reservationList = mutableListOf<Reservation>()
        for (i in 1..30) {
            reservationList.add(Reservation(i.toLong(), User(i.toLong(), "수강자$i"), lecture))
        }
        `when`(reservationRepository.getAllReservationsByLectureId(lectureId)).thenReturn(reservationList)

        // when - then
        Assertions.assertThrows(Exception::class.java) {
            reservationService.reserveLecture(lectureId, 31)
        }
    }
}