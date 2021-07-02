package com.example.demo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
//@Transactional(readOnly = true)
interface UserRepository : CrudRepository<UserEntity, Long> {

    fun findByUserId(id: Long): UserEntity?
}
