package com.meshal.meshalminibank.KYC

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name="kyc")
data class KycEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    val dateOfBirth: LocalDate,
    val nationality: String,
    val salary: BigDecimal
){
   constructor() : this(null, 0, LocalDate.now(),"", BigDecimal.ZERO)
}