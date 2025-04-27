package com.meshal.meshalminibank

import com.meshal.meshalminibank.Accounts.AccountsEntity
import com.meshal.meshalminibank.Accounts.AccountsRepository
import com.meshal.meshalminibank.Users.UsersEntity
import com.meshal.meshalminibank.Users.UsersRepository
import com.meshal.meshalminibank.authentication.JWT.JwtService
import io.cucumber.java.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.security.crypto.password.PasswordEncoder
import java.math.BigDecimal
import java.util.*

//class BakingServiceSteps {
//    @Autowired
//    private lateinit var jwtService: JwtService
//
//    @Autowired
//    private lateinit var testRestTemplate: TestRestTemplate
//
//    @Autowired
//    private lateinit var usersRepository: UsersRepository
//
//    @Autowired
//    private lateinit var accountsRepository: AccountsRepository
//
//    @Autowired
//    private lateinit var passwordEncoder: PasswordEncoder
//
//    private val testUUID1 : UUID = UUID.fromString("2b8bc165-a641-48d5-adc0-ce5e5512da7a")
//    private val testUUID2 : UUID = UUID.fromString("3f0dd228-9613-44b1-908c-e3f828b79fa3")
//
//
//    @Before
//    fun setupTestData() {
//        val user1 = UsersEntity(username = "ahjadi", password = passwordEncoder.encode("Test12345"))
//        usersRepository.save(user1)
//        val user2 = UsersEntity(username = "yoyoyo", password = passwordEncoder.encode("Test12345"))
//        usersRepository.save(user2)
//        val testAccountSource = AccountsEntity(
//            name = String,
//            balance = BigDecimal,
//            isActive = Boolean,
//            accountNumber = String = UUID.randomUUID().toString(),
//            user = user1
//        )
//        val testAccountDestination = AccountsEntity(
//            user = user2,
//            balance = BigDecimal("500"),
//            isActive = true,
//            name = "test2",
//            accountNumber = testUUID2.toString(),
//        )
//        accountsRepository.save(testAccountSource)
//        accountsRepository.save(testAccountDestination)
//
//    }
//}