package kr.tareun.hhplus.reservation.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import kr.tareun.hhplus.lecture.model.Lecture
import kr.tareun.hhplus.user.model.User

@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    var user: User? = null,

    @ManyToOne
    var lecture: Lecture? = null
)