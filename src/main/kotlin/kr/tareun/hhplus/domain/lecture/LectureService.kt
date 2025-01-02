package kr.tareun.hhplus.domain.lecture

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate

@Transactional
@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    private val lectureMapper: LectureMapper
    ) {
    // 특강 조회
    fun getLectureById(id: Long): LectureResponse = lectureMapper.toLectureResponse(lectureRepository.getLecture(id))

    // 날짜별 특강 조회
    fun getLecturesByDate(date: LocalDate): List<LectureResponse> = lectureRepository.getAllLecturesByDate(date).map { lectureMapper.toLectureResponse(it) }

    // 특강 추가
    fun addLecture(lecture: LectureRequest): LectureResponse = lectureMapper.toLectureResponse(
        lectureRepository.save(lectureMapper.toLectureEntity(lecture))
    )
}