package kr.tareun.hhplus.lecture.model

import jakarta.persistence.*
import kr.tareun.hhplus.reservation.model.Reservation
import java.time.LocalDateTime

@Entity
class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var startTime: LocalDateTime? = null,

    @Column(nullable = false)
    var endTime: LocalDateTime? = null,

    @OneToMany(mappedBy = "lecture")
    var reservationList: MutableList<Reservation> = mutableListOf()
) {
    fun addReservation(reservation: Reservation) {
        reservationList.add(reservation)
    }
}