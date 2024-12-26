package kr.tareun.hhplus.domain.reservation

interface ReservationRepository {
    fun getAllReservationsByUserId(userId: Long): List<Reservation>
    fun save(reservation: Reservation): Reservation
}