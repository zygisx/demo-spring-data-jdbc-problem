package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class UserEntity(
    @field:Id val userId: Long,
    val username: String,

    @MappedCollection(idColumn = "user_id", keyColumn = "user_id")
    val emails: Set<UserEmailAddress> = mutableSetOf(),
)

@Table("user_email_address")
data class UserEmailAddress(
    val email: String,
)