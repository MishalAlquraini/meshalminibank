package com.meshal.meshalminibank.KYC


import com.meshal.meshalminibank.Users.UsersServices
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface KYCRepository : JpaRepository<KycEntity, Long>

//{
//    fun findAllByUserId(userId:Long): List<KycEntity>
//}
