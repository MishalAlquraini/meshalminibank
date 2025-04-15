package com.meshal.meshalminibank.Accounts


import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface AccountsRepository : JpaRepository<AccountsEntity, Long>
//{
//    fun findByAccountNumberEqual(accountNumber:Long) : List<AccountsEntity>
//}
