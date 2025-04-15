package com.meshal.meshalminibank.Accounts

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class TransactionsController(
    val transactionsRepo: TransactionsRepository,
    val accountsRepo: AccountsRepository
){
    @PostMapping("/accounts/v1/accounts/transfer")
    fun transfer(@RequestBody request: transferRequest): TransactionsEntity{
        val sourceAccount = accountsRepo.findById(request.sourceAccount).orElseThrow{ RuntimeException ("Source not found")}

        val destinationAcc = accountsRepo.findById(request.destinationAccount).orElseThrow{ RuntimeException("Destination not found")}

        if (sourceAccount.balance < request.amount){
            throw RuntimeException ("insufficient funds")
        }
        accountsRepo.save(sourceAccount.copy(balance = sourceAccount.balance - request.amount))
        accountsRepo.save(destinationAcc.copy(balance = destinationAcc.balance + request.amount))

        val transaction = TransactionsEntity(
            amount = request.amount,
            sourceAccount = request.sourceAccount,
            destinationAccount = request.destinationAccount
        )
        return transactionsRepo.save(transaction)
    }

    @GetMapping("/list/transactions")
    fun showTransactions(): List<TransactionsEntity> =
        transactionsRepo.findAll()
}

data class transferRequest(
    val amount: BigDecimal,
    val sourceAccount: Long,
    val destinationAccount: Long
)