package kr.tareun.hhplus.lecture.repository

import kr.tareun.hhplus.lecture.model.Lecture
import org.springframework.stereotype.Repository

@Repository
class LectureCoreRepository: LectureRepository {
    override fun getAllLectures(): List<Lecture> {
        TODO("Not yet implemented")
    }

    override fun getLecture(lectureId: Long): Lecture {
        TODO("Not yet implemented")
    }

    override fun getLectureWithReservationsById(lectureId: Long): Lecture {
        TODO("Not yet implemented")
    }

    override fun save(lecture: Lecture): Lecture {
        TODO("Not yet implemented")
    }

}