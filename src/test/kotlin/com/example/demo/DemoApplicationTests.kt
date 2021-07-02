package com.example.demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	lateinit var repository: UserRepository

	@AfterEach
	fun cleanUpDb() {
		repository.deleteAll()
	}

	@Test
	fun `should handle 10 queries at the same time`() {
		val inserted = repository.save(UserEntity(0, "username", emails = setOf(UserEmailAddress("test@test.com"))))

		val id = inserted.userId

		runBlocking(Dispatchers.IO) {
			(0 until 10).map {
				async {
					repository.findByUserId(id)
				}
			}.awaitAll()
		}
	}

	@Test
	fun `should handle 9 queries at the same time`() {
		val inserted = repository.save(UserEntity(0, "username", emails = setOf(UserEmailAddress("test@test.com"))))

		val id = inserted.userId

		runBlocking(Dispatchers.IO) {
			(0 until 9).map {
				async {
					repository.findByUserId(id)
				}
			}.awaitAll()
		}
	}

}
