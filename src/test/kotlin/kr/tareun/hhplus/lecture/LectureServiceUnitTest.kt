package kr.tareun.hhplus.lecture

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.lecture.repository.LectureRepository
import kr.tareun.hhplus.lecture.service.LectureService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

@Suppress("NonAsciiCharacters")
class LectureServiceUnitTest {
    @Mock
    lateinit var lectureRepository: LectureRepository

    @InjectMocks
    lateinit var lectureService: LectureService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `특강 목록을 조회할 수 있다`() {
        // given
        val lecture1 = Lecture(1, "특강1", LocalDateTime.now(), LocalDateTime.now())
        val lecture2 = Lecture(2, "특강2", LocalDateTime.now(), LocalDateTime.now())
        val lectureList = listOf(lecture1, lecture2)
        `when`(lectureRepository.getAllLectures()).thenReturn(lectureList)

        // when - then
        Assertions.assertEquals(lectureList[0].id, lecture1.id)
        Assertions.assertEquals(lectureList[1].id, lecture2.id)
    }

    @Test
    fun `특강을 조회할 수 있다`() {
        // given
        val start = LocalDateTime.of(2025, 1, 1, 10, 30)
        val end = LocalDateTime.of(2025, 1, 1, 12, 30)
        val lecture = Lecture(1, "특강1", start, end)
        `when`(lectureRepository.getLecture(lecture.id!!)).thenReturn(lecture)

        // when - then
        Assertions.assertEquals(lecture.id, lectureService.getLecture(lecture.id!!).id)
    }

    @Test
    fun `특강을 추가할 수 있다`() {
        // given
        val start = LocalDateTime.of(2025, 1, 1, 10, 30)
        val end = LocalDateTime.of(2025, 1, 1, 12, 30)
        val lecture = Lecture(1, "특강1", start, end)
        `when`(lectureRepository.save(lecture)).thenReturn(lecture)

        // when - then
        Assertions.assertEquals(lecture.id, lectureService.addLecture(lecture).id)
    }
}