package kr.tareun.hhplus.domain.user

import jakarta.persistence.*
import kr.tareun.hhplus.domain.common.BaseEntity

@Table(name = "app_user")
@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String = "",
): BaseEntity()