package com.meshal.meshalminibank.Users

import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface UsersRepository : JpaRepository<UsersEntity, Long> {
    abstract fun findByUsername(username: String): UsersEntity?
}




