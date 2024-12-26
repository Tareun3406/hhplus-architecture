package kr.tareun.hhplus.domain.reservation

import org.springframework.stereotype.Component

@Component
class ReservationMapper {
    fun toReservationResponse(reservation: Reservation): ReservationResponse = ReservationResponse(
        reservationId = reservation.id!!,
        userId = reservation.userId!!,
        lectureId = reservation.lecture!!.id!!,
        lectureName = reservation.lecture!!.name!!,
        lecturer = reservation.lecture!!.lecturer!!,
    )
}