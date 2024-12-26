package kr.tareun.hhplus.domain.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Suppress("unused")
@MappedSuperclass
class BaseEntity(
    @Column(nullable = false)
    @CreationTimestamp
    var timeCreated: LocalDateTime? = null,

    @Column(nullable = false)
    @UpdateTimestamp
    var timeUpdated: LocalDateTime? = null,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null
)