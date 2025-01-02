package kr.tareun.hhplus.domain.reservation

interface ReservationRepository {
    fun existsByUserIdAndLectureId(userId: Long, lectureId: Long): Boolean
    fun getAllReservationsByUserId(userId: Long): List<Reservation>
    fun save(reservation: Reservation): Reservation
}