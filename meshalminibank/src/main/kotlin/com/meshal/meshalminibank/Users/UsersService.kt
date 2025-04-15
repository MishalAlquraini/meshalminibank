package com.meshal.meshalminibank.Users

import jakarta.inject.Named

@Named
class UsersServices(
    val usersRepository: UsersRepository
){

    fun requestUsers(request: UserRequest) = usersRepository.save(UsersEntity(username = request.username, password = request.password))
}

data class UserRequest(
    val username: String,
    val password: String
)
