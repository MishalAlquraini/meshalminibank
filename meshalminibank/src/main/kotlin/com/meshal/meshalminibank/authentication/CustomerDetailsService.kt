package com.meshal.meshalminibank.authentication

import com.meshal.meshalminibank.Users.UsersEntity
import com.meshal.meshalminibank.Users.UsersRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerDetailsService(
    private val userRepo : UsersRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user: UsersEntity = userRepo.findByUsername(username) ?:
        throw UsernameNotFoundException("User not found ...")

        return User.builder()
            .username(user.username)
            .password(user.password)
            .build()
    }

}