package kr.tareun.hhplus.service.lecture

import kr.tareun.hhplus.domain.lecture.LectureRequest
import kr.tareun.hhplus.domain.lecture.LectureService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
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
import java.time.LocalDateTime
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
@Testcontainers
@SpringBootTest
class LectureServiceIntegrationTest {
    @Autowired
    private lateinit var lectureService: LectureService

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

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
    fun `특강 정보를 조회할 수 있다`() {
        // given
        val lectureId = 1L

        // when - then
        Assertions.assertEquals(lectureId, lectureService.getLectureById(lectureId).id)
    }

    @Test
    fun `특강 목록을 조회 할 수 있다`() {
        // given
        val targetDate = LocalDate.parse("2030-10-11")

        // when
        val result = lectureService.getLecturesByDate(targetDate)

        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `특강을 추가할 수 있다`() {
        // given
        val lectureRequest = LectureRequest("특강 3", "강연자 3", LocalDateTime.now(), LocalDateTime.now().plusHours(1))

        // when
        val result = lectureService.addLecture(lectureRequest)

        // then
        Assertions.assertEquals(lectureRequest.name, result.name)
    }
}