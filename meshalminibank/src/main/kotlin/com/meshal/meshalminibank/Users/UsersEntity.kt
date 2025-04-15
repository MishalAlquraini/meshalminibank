package com.meshal.meshalminibank.Users

import com.meshal.meshalminibank.Accounts.AccountsEntity
import jakarta.persistence.*

@Entity
@Table(name="users")
data class UsersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val password: String
){
    constructor() : this(null, "", "")
}