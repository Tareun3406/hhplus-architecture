package kr.tareun.hhplus.interfaces

import kr.tareun.hhplus.domain.reservation.ReservationResponse
import kr.tareun.hhplus.domain.reservation.ReservationService
import org.springframework.web.bind.annotation.*

@RequestMapping("/reservations")
@RestController
class ReservationController(private val reservationService: ReservationService) {
    // fixme 특강 예약
    @PostMapping("/lectures/{lectureId}")
    fun reserveLecture(@PathVariable lectureId: Long, @RequestBody userId: Long): ReservationResponse = reservationService.reserveLecture(lectureId, userId)

    // 신청 완료된 강의 리스트를 응답 데이터로 줍니다.
    // fixme 신청 완료 목록 조회
    @GetMapping("/users/{userId}")
    fun getReservations(@PathVariable("userId") userId: Long): List<ReservationResponse> = reservationService.getReservationsByUserId(userId)
}