package kr.tareun.hhplus.domain.lecture

import org.springframework.stereotype.Component

@Component
class LectureMapper() {
    fun toLectureResponse(lecture: Lecture): LectureResponse = LectureResponse(
        id = lecture.id!!,
        name = lecture.name!!,
        lecturer = lecture.lecturer!!,
        startTime = lecture.startTime!!,
        endTime = lecture.endTime!!,
        reservedCounts = lecture.reservedCounts,
        maxReservations = lecture.maxReservation
    )

    fun toLectureEntity(lectureInfo: LectureRequest): Lecture = Lecture(
        name = lectureInfo.name,
        lecturer = lectureInfo.lecturer,
        startTime = lectureInfo.startTime,
        endTime = lectureInfo.endTime,
        reservedCounts = 0,
    )
}