package kr.tareun.hhplus.service.reservation

import kr.tareun.hhplus.infrastructure.reservation.ReservationJpaRepository
import kr.tareun.hhplus.domain.reservation.ReservationService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.sql.SQLException
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Suppress("NonAsciiCharacters")
@SpringBootTest
@Testcontainers
class ReservationIntegrationTest {
    companion object {
        private const val USERNAME = "root"
        private const val PASSWORD = "test1234"
        private const val SCHEMA_NAME = "app"

        @Container
        private val mariadb = GenericContainer(DockerImageName.parse("mariadb:11.4"))
            .withExposedPorts(3306)
            .withEnv("MARIADB_ROOT_PASSWORD", PASSWORD)
            .withEnv("MARIADB_DATABASE", SCHEMA_NAME)
            .withReuse(false)

        @Suppress("unused")
        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") {
                "jdbc:mariadb://${mariadb.host}:${mariadb.firstMappedPort}/$SCHEMA_NAME"
            }
            registry.add("spring.datasource.username") { USERNAME }
            registry.add("spring.datasource.password") { PASSWORD }
        }
    }

    @Autowired
    private lateinit var reservationService: ReservationService

    @Autowired
    private lateinit var reservationJpaRepository: ReservationJpaRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun resetDatabase() {
        val resource = ClassPathResource("init.sql")
        try {
            ScriptUtils.executeSqlScript(jdbcTemplate.dataSource!!.connection, resource)
        } catch (ex: SQLException) {
            throw RuntimeException("Database reset failed: ${ex.message}", ex)
        }
    }

    @Test
    fun `예약한 특강 목록을 확인할 수 있다`() {
        // given
        val userId = 51L

        // when
        val getResult = reservationService.getReservationsByUserId(userId)

        // then
        Assertions.assertEquals(1, getResult.size)
    }

    @Test
    fun `특강을 신청 할 수 있다`() {
        val userId = 1L
        val lectureId = 1L

        // when
        val reserveResult = reservationService.reserveLecture(lectureId, userId)

        // then
        Assertions.assertEquals(userId, reserveResult.userId)
        Assertions.assertEquals(lectureId, reserveResult.lectureId)
    }

    @Test
    fun `동시에 특강을 예약할 경우 선착순 30명만 성공합니다`() {
        // given
        val logger: Logger = LoggerFactory.getLogger(ReservationIntegrationTest::class.java)
        val lectureId = 1L
        val tasks = mutableListOf<Runnable>()
        for (i in 1..50) {
            tasks.add(Runnable {
                try {
                    reservationService.reserveLecture(lectureId, i.toLong())
                } catch (e: Exception) {
                    logger.error(e.message)
                }
            })
        }

        // when
        val futures = mutableListOf<CompletableFuture<Void>>()
        tasks.forEach { futures.add(CompletableFuture.runAsync(it)) }
        CompletableFuture.allOf(*futures.toTypedArray()).join()

        // then
        Assertions.assertEquals(30, reservationJpaRepository.getAllByLectureId(lectureId).size)
    }

    @Test
    fun `같은 사용자가 여러번 특강을 신청할 경우 한번만 성공합니다`() {
        // given
        val logger: Logger = LoggerFactory.getLogger(ReservationIntegrationTest::class.java)
        val lectureId = 1L
        val userId = 1L
        val tasks = mutableListOf<Runnable>()
        for (i in 1..5) { // 5회 테스트
            tasks.add(Runnable {
                try {
                    reservationService.reserveLecture(lectureId, userId)
                } catch (e: Exception) {
                    logger.error(e.message)
                }
            })
        }

        // when
        val futures = mutableListOf<CompletableFuture<Void>>()
        tasks.forEach { futures.add(CompletableFuture.runAsync(it)) }
        CompletableFuture.allOf(*futures.toTypedArray()).join()

        // then
        Assertions.assertEquals(1, reservationJpaRepository.getAllByUserId(userId).size)
    }
}