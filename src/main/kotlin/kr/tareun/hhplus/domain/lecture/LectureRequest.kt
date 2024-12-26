package kr.tareun.hhplus.domain.lecture

import java.time.LocalDateTime

data class LectureRequest(
    val name: String,
    val lecturer: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
