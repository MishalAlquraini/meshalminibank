package com.meshal.meshalminibank.Accounts

import com.meshal.meshalminibank.Users.UsersEntity
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*


@Entity
@Table(name="accounts")
data class AccountsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    // foreign key
    var userId: Long,
    var name: String,
    var balance: BigDecimal,
    var isActive: Boolean,
    var accountNumber: String = UUID.randomUUID().toString()
){
    constructor() : this(null, 0, "",BigDecimal.ZERO,true,"")
}