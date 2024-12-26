package kr.tareun.hhplus.domain.reservation

data class ReservationResponse(
    val reservationId: Long,
    val userId: Long,
    val lectureId: Long,
    val lectureName: String,
    val lecturer: String,
)