package com.meshal.meshalminibank.Accounts

import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface TransactionsRepository : JpaRepository<TransactionsEntity, Long>{
   // fun findAllWhereSourceAccount(accountId:Long) : List<TransactionsEntity>
    // fun findAllWhereDestinationAccount(accountId:Long) : List<TransactionsEntity>
}