package com.meshal.meshalminibank.Accounts

import com.meshal.meshalminibank.Users.UsersRepository
import com.meshal.meshalminibank.authentication.CustomerDetailsService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.io.InvalidObjectException
import java.math.BigDecimal

@RestController
class AccountsController(
    val accountsRepo: AccountsRepository,
    val userRepository: UsersRepository
){
    @PostMapping("/accounts/v1/accounts")
        fun createAccount(@RequestBody request: AccRequest, @AuthenticationPrincipal principal: UserDetails): Any {
        val user = userRepository.findByUsername(principal.username)
        if (request.balance < BigDecimal(500)){
        return ResponseEntity.badRequest().body("Balance less then 500 not allowed")
        }

        return AccountsEntity(
            userId = user?.id!!,
            name = request.name,
            balance = request.balance,
            isActive = true
        )
    }
    @PostMapping("/accounts/v1/accounts/{accountNumber}/close")
    fun closeAccount(@PathVariable accountNumber: String){
        val account = accountsRepo.findAll().firstOrNull{it.accountNumber == accountNumber}
            ?: throw RuntimeException("Account not found")

        accountsRepo.save(account.copy(isActive = false))
    }
    @GetMapping("/accounts/v1/list")
    fun listAllAccounts(): List<AccountsEntity> = accountsRepo.findAll()
}

data class AccRequest(
   // val userId: Long,
    val name: String,
    val balance: BigDecimal,
)

data class ErrorDto(
val message:String
)

