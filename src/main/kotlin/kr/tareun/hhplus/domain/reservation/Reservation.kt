package kr.tareun.hhplus.domain.reservation

import jakarta.persistence.*
import kr.tareun.hhplus.domain.lecture.Lecture
import kr.tareun.hhplus.domain.common.BaseEntity

@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var userId: Long? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    var lecture: Lecture? = null,
): BaseEntity()