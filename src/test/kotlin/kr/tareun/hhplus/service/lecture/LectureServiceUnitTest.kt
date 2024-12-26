package kr.tareun.hhplus.service.lecture

import kr.tareun.hhplus.domain.lecture.LectureMapper
import kr.tareun.hhplus.domain.lecture.Lecture
import kr.tareun.hhplus.domain.lecture.LectureRequest
import kr.tareun.hhplus.domain.lecture.LectureRepository
import kr.tareun.hhplus.domain.lecture.LectureService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Suppress("NonAsciiCharacters")
class LectureServiceUnitTest {
    @Mock
    lateinit var lectureRepository: LectureRepository

    private val lectureMapper: LectureMapper = LectureMapper()
    private lateinit var lectureService: LectureService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        lectureService = LectureService(lectureRepository, lectureMapper)
    }

    @Test
    fun `특강을 조회할 수 있다`() {
        // given
        val date = LocalDate.now()
        val time = LocalTime.now()
        val lecture = Lecture(1, "특강1", "강연자1", LocalDateTime.of(date, time), LocalDateTime.of(date, time.plusHours(1)))
        `when`(lectureRepository.getLecture(any())).thenReturn(lecture)

        // when - then
        Assertions.assertEquals(lecture.id, lectureService.getLectureById(lecture.id!!).id)
    }

    @Test
    fun `특강 목록을 조회할 수 있다`() {
        // given
        val date = LocalDate.now()
        val time = LocalTime.now()
        val lecture1 = Lecture(1, "특강1", "강연자1", LocalDateTime.of(date, time), LocalDateTime.of(date, time.plusHours(1)))
        val lecture2 = Lecture(2, "특강2", "강연자2", LocalDateTime.of(date, time), LocalDateTime.of(date, time.plusHours(1)))
        val lectureList = listOf(lecture1, lecture2)
        `when`(lectureRepository.getAllLecturesByDate(date)).thenReturn(lectureList)

        // when
        val result = lectureService.getLecturesByDate(LocalDate.now())

        // then
        Assertions.assertEquals(lectureList[0].id, result[0].id)
        Assertions.assertEquals(lectureList[1].id, result[1].id)
    }

    @Test
    fun `특강을 추가할 수 있다`() {
        // given
        val start = LocalDateTime.of(2025, 1, 1, 10, 30)
        val end = LocalDateTime.of(2025, 1, 1, 12, 30)
        val request = LectureRequest("특강1", "강연자1",start, end)
        val lecture = Lecture(1, "특강1", "강연자1",start, end)
        `when`(lectureRepository.save(any())).thenReturn(lecture)

        // when - then
        Assertions.assertEquals(lecture.id, lectureService.addLecture(request).id)
    }
}