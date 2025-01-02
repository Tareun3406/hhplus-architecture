package kr.tareun.hhplus.service.user

import kr.tareun.hhplus.domain.user.UserRequest
import kr.tareun.hhplus.domain.user.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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

@Suppress("NonAsciiCharacters")
@Testcontainers
@SpringBootTest
class UserServiceIntegrationTest {
    @Autowired
    private lateinit var userService: UserService

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
    fun `유저 정보를 조회할 수 있다`() {
        // given
        val userId = 1L

        Assertions.assertEquals(userId, userService.getUser(userId).userId)
    }

    @Test
    fun `유저를 추가할 수 있다`() {
        // given
        val userName = "사용자 51"

        // then
        val result = userService.addUser(UserRequest(userName))

        // then
        Assertions.assertEquals(userName, result.userName)
    }
}