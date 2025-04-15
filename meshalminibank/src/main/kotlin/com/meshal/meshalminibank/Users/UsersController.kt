package com.meshal.meshalminibank.Users


import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    val usersServices: UsersServices
){

    @PostMapping("/users/v1/register")
    fun users(@RequestBody request: userRequest) = usersServices.requestUsers(request)
}