package kr.tareun.hhplus.domain.reservation

import jakarta.transaction.Transactional
import kr.tareun.hhplus.domain.lecture.LectureRepository
import org.springframework.stereotype.Service

@Transactional
@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val lectureRepository: LectureRepository,

    private val reservationMapper: ReservationMapper,
) {
    // 특강 신청
    fun reserveLecture(lectureId: Long, userId: Long): ReservationResponse {
        val lecture = lectureRepository.getLectureForUpdate(lectureId)

        if (reservationRepository.existsByUserIdAndLectureId(userId, lectureId)) {
            throw IllegalArgumentException("이미 예약한 특강입니다.")
        } else if (lecture.reservedCounts >= lecture.maxReservation) {
            throw IllegalArgumentException("신청 마감 되었습니다.")
        }

        lecture.addReservedCounts()
        return reservationMapper.toReservationResponse(reservationRepository.save(Reservation(null, userId, lecture)))
    }

    // 신청완료 내역 조회
    fun getReservationsByUserId(userId: Long): List<ReservationResponse> = reservationRepository.getAllReservationsByUserId(userId).map { reservationMapper.toReservationResponse(it) }
}