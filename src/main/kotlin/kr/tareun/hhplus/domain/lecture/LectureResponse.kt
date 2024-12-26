package kr.tareun.hhplus.domain.lecture

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class LectureResponse(
    val id: Long,
    val name: String,
    val lecturer: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val reservedCounts: Int,
    val maxReservations: Int,
) {
    @JsonProperty("reserveAvailable")
    fun reserveAvailable() = reservedCounts < maxReservations
}
