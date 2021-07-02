package com.example.demo

import liquibase.integration.spring.SpringLiquibase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@Configuration
class AppConfiguration {
	@Bean
	fun liquibase(dataSource: DataSource): SpringLiquibase? {
		val liquibase = SpringLiquibase()
		liquibase.dataSource = dataSource
		liquibase.changeLog = "classpath:db/changelog/db.changelog-master.xml"
		return liquibase
	}
}
