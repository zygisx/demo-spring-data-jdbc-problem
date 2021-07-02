package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(
    val userRepository: UserRepository
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable(value = "id") userId: Long): UserEntity {
        return userRepository.findByUserId(userId) ?: UserEntity(0, "NOTFOUND")
    }
}