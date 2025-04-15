package com.meshal.meshalminibank.Users

import jakarta.inject.Named
import org.springframework.web.bind.annotation.RequestBody

@Named
class UsersServices(
    val usersRepository: UsersRepository
){

    fun requestUsers(request: userRequest) = usersRepository.save(UsersEntity(username = request.username, password = request.password))
}

data class userRequest(
    val username: String,
    val password: String
)
