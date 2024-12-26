package kr.tareun.hhplus.service.reservation

import kr.tareun.hhplus.domain.reservation.ReservationMapper
import kr.tareun.hhplus.domain.lecture.Lecture
import kr.tareun.hhplus.domain.reservation.Reservation
import kr.tareun.hhplus.domain.lecture.LectureRepository
import kr.tareun.hhplus.domain.reservation.ReservationRepository
import kr.tareun.hhplus.domain.reservation.ReservationService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import java.time.LocalDateTime

@Suppress("NonAsciiCharacters")
class ReservationServiceUnitTest {

    @Mock
    lateinit var reservationRepository: ReservationRepository

    @Mock
    lateinit var lectureRepository: LectureRepository

    private val reservationMapper: ReservationMapper = ReservationMapper()
    private lateinit var reservationService: ReservationService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        reservationService = ReservationService(reservationRepository, lectureRepository, reservationMapper)
    }

    @Test
    fun `수강자가 특강을 신청할 수 있다`() {
        // given
        val userId = 1L
        val lectureId = 1L
        val lecture = Lecture(lectureId, "강의1", "강연자1", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 29)
        val reservation = Reservation(1, userId, lecture)

        `when`(lectureRepository.getLecture(lectureId)).thenReturn(lecture)
        `when`(reservationRepository.save(any())).thenReturn(reservation)

        // when
        val resultReservation = reservationService.reserveLecture(userId, lectureId)

        // then
        Assertions.assertEquals(reservation.userId, resultReservation.userId)
        Assertions.assertEquals(reservation.lecture!!.id, resultReservation.lectureId)
    }

    @Test
    fun `수강자 ID로 신청한 특강 목록을 조회할 수 있다`() {
        // given
        val userId = 1L
        val reservationList = listOf(
            Reservation(1L, userId, Lecture(1L, "특강1", "강연자1")),
            Reservation(2L, userId, Lecture(2L, "특강 2", "강연자2")),
        )
        `when`(reservationRepository.getAllReservationsByUserId(userId)).thenReturn(reservationList)

        // when
        val result = reservationService.getReservationsByUserId(userId)

        // then
        Assertions.assertEquals(reservationList[0].id, result[0].reservationId)
        Assertions.assertEquals(reservationList[1].id, result[1].reservationId)
    }
}