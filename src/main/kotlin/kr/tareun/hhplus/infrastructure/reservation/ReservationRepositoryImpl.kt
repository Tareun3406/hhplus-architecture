package kr.tareun.hhplus.infrastructure.reservation

import kr.tareun.hhplus.domain.reservation.Reservation
import kr.tareun.hhplus.domain.reservation.ReservationRepository
import org.springframework.stereotype.Repository

@Repository
class ReservationRepositoryImpl(private val jpaRepository: ReservationJpaRepository): ReservationRepository {
    override fun getAllReservationsByUserId(userId: Long): List<Reservation> = jpaRepository.getAllByUserId(userId)

    override fun save(reservation: Reservation): Reservation = jpaRepository.save(reservation)

}