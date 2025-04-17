package com.meshal.meshalminibank.Users

import jakarta.inject.Named
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.http.ResponseEntity
import java.lang.IllegalArgumentException

const val MAX_CHAR = 10
const val MIN_CHAR = 6

@Named
class UsersServices(
    val usersRepository: UsersRepository
){

    fun requestUsers(request: UserRequest): Any
    {
        if (request.username.length > MAX_CHAR){
            throw IllegalArgumentException("username must be less than 12 characters")
        }
        if(request.password.length < MIN_CHAR){
            throw IllegalArgumentException("password must be at least 6 characters")
        }
        val user  = UsersEntity(username = request.username, password = request.password)
        usersRepository.save(user)
       return user.username

    }

}

data class UserRequest(
    val username: String,
    val password: String
)
