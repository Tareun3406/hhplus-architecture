package kr.tareun.hhplus.lecture.service

import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.lecture.repository.LectureRepository
import org.springframework.stereotype.Service

@Service
class LectureService(private val lectureRepository: LectureRepository) {
    fun getAllLectures(): List<Lecture> {
        return lectureRepository.getAllLectures();
    }

    fun getLecture(lectureId: Long): Lecture {
        return lectureRepository.getLecture(lectureId);
    }

    fun addLecture(lecture: Lecture): Lecture {
        return lectureRepository.save(lecture)
    }
}