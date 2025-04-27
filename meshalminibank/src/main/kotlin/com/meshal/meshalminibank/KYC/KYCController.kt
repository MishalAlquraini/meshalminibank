package com.meshal.meshalminibank.KYC

import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@RestController
class KYCController(
    val kycRepo: KYCRepository,
){
    @PostMapping("/users/v1/kyc")
    fun createOrUpdateKyc(@RequestBody request: KYCRequest): KycEntity{
        val kyc = kycRepo.findAll().firstOrNull{it.userId == request.userId}

            ?: KycEntity(
            userId = request.userId,
            dateOfBirth = request.dateOfBirth,
            nationality = request.nationality,
            salary = request.salary
        )
        return kycRepo.save(
            kyc.copy(
                userId = request.userId,
                dateOfBirth = request.dateOfBirth,
                nationality = request.nationality,
                salary = request.salary,
            )
        )
    }

    @GetMapping("/users/v1/kyc/{userId}")
    fun findKycByUserId(@PathVariable userId: Long): KycEntity{
       return kycRepo.findById(userId).get()
    }
}

data class KYCRequest(
    val userId: Long,
    val dateOfBirth: Date,
    val nationality: String,
    val salary: BigDecimal
)

data class Kyc(
    val userId: Long,
    val name: String,
    val nationality: String,
    val salary: BigDecimal
)