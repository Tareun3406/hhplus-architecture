package kr.tareun.hhplus.interfaces

import kr.tareun.hhplus.domain.lecture.LectureRequest
import kr.tareun.hhplus.domain.lecture.LectureResponse
import kr.tareun.hhplus.domain.lecture.LectureService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RequestMapping("/lectures")
@RestController
class LectureController(private val lectureService: LectureService) {

    // 특강 조회
    @GetMapping("/{lectureId}")
    fun getLecture(@PathVariable lectureId: Long): LectureResponse = lectureService.getLectureById(lectureId)

    // 쿼리 파라미터로 날짜를 지정하여 해당 날짜에 해당하는 특강 목록을 리턴
    // fixme 날짜 별 신청 가능한 특강 목록 조회(각 특강 정보에 신청 가능 여부가 포함됨)
    @GetMapping("/date")
    fun getLectures(@RequestParam date: LocalDate): List<LectureResponse> = lectureService.getLecturesByDate(date)

    // 특강 추가
    @PostMapping
    fun addLecture(@RequestBody lecture: LectureRequest): LectureResponse = lectureService.addLecture(lecture)
}