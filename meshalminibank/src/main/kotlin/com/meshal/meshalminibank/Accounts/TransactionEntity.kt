package com.meshal.meshalminibank.Accounts


import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name="transactions")
data class TransactionsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val amount:BigDecimal,

//    F-Keys in DB
    val sourceAccount: Long,
    val destinationAccount: Long

){
    constructor() : this(null, BigDecimal.ZERO,0,0)
}