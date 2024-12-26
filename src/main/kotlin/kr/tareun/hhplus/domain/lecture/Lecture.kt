package kr.tareun.hhplus.domain.lecture

import jakarta.persistence.*
import kr.tareun.hhplus.domain.common.BaseEntity
import java.time.LocalDateTime

@Entity
class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var lecturer: String? = null,

    @Column(nullable = false)
    var startTime: LocalDateTime? = null,

    @Column(nullable = false)
    var endTime: LocalDateTime? = null,

    @Column(nullable = false)
    var reservedCounts: Int = 0, // 특강 신청자 수 체크용 컬럼

    @Column(nullable = false)
    var maxReservation: Int = 30
): BaseEntity() {
    fun addReservedCounts() {
        reservedCounts++
    }
}