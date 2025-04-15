package com.meshal.meshalminibank.Accounts

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class AccountsController(
    val accountsRepo: AccountsRepository
){
    @PostMapping("/accounts/v1/accounts")
    fun createAccount(@RequestBody request: accRequest): AccountsEntity{
        val newAcc = AccountsEntity(
            userId = request.userId,
            balance = request.balance,
            isActive = request.isActive,
            accountNumber = request.accountNumber
        )
        return accountsRepo.save(newAcc)
    }
    @PostMapping("/accounts/v1/accounts/{accountNumber}/close")
    fun closeAccount(@PathVariable accountNumber: String){
        val account = accountsRepo.findAll().firstOrNull{it.accountNumber == accountNumber}
            ?: throw RuntimeException("Account not found")

        accountsRepo.save(account.copy(isActive = false))
    }
    @GetMapping("/accounts/v1/accounts")
    fun listAllAccounts(): List<AccountsEntity> = accountsRepo.findAll()
}

data class accRequest(
    val userId: Long,
    val balance: BigDecimal,
    val isActive: Boolean,
    val accountNumber: String
)