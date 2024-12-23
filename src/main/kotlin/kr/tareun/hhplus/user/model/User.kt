package kr.tareun.hhplus.user.model

import jakarta.persistence.*
import kr.tareun.hhplus.reservation.model.Reservation

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String = "",

    @OneToMany(mappedBy = "user")
    var reservationList: MutableList<Reservation> = mutableListOf()
)