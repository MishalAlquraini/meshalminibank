package com.meshal.meshalminibank

import com.meshal.meshalminibank.Accounts.AccRequest
import com.meshal.meshalminibank.Accounts.AccountsEntity
import com.meshal.meshalminibank.Accounts.AccountsRepository
import com.meshal.meshalminibank.Users.UserRequest
import com.meshal.meshalminibank.Users.UsersEntity
import com.meshal.meshalminibank.Users.UsersRepository
import com.meshal.meshalminibank.authentication.JWT.AuthenticationRequest
import com.meshal.meshalminibank.authentication.JWT.AuthenticationResponse
import com.meshal.meshalminibank.authentication.JWT.JwtService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.MultiValueMap
import java.math.BigDecimal
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MeshalminibankApplicationTests{

	@Autowired
	lateinit var restTemplate: TestRestTemplate
	@Autowired
	lateinit var usersRepository: UsersRepository
	@Autowired
	lateinit var passwordEncoder: PasswordEncoder
	@Autowired
	lateinit var accountsRepository: AccountsRepository

	@Autowired
	lateinit var jwtService: JwtService
	companion object {
		val testusername = "meshal"
		val testpassword = "99775283"
	}

	@BeforeEach
	fun setUp(){
		usersRepository.deleteAll()
		val testUser = UsersEntity(
			username = "meshal",
			password = passwordEncoder.encode("99775283")
		)
			val savedUser = usersRepository.save(testUser)
			print("savedUser ${savedUser.id}")
	}

	@Test
	fun register(){
		val registration = UserRequest(
			username = testusername,
			password = testpassword
		)
		val requestEntity = HttpEntity<UserRequest>(registration)
		val result = restTemplate.exchange(
			"/register",
			HttpMethod.POST,
			requestEntity,
			String::class.java
		)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(testusername,result.body)
	}



	@Test
	fun testValidToken(){
		createToken()
	}

	fun createToken():String{
		val mockLogin = AuthenticationRequest(
			username = testusername,
			password = testpassword
		)
		val response: ResponseEntity<AuthenticationResponse> = restTemplate.postForEntity(
			"/auth/login",
			mockLogin,
			AuthenticationResponse::class.java
		)
		assertEquals(HttpStatus.OK,response.statusCode)
		return response.body?.token ?: ""
	}



	fun createAccount(): AccountsEntity {
//		val token = createToken()
//		val headers = HttpHeaders(
//			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
//		)
//		val mockAccount = AccRequest(
//			userId = 1,
//			name = "Debit Account",
//				balance = BigDecimal(1200.0),
//		)
//		val response = restTemplate.exchange(
//			"/accounts/v1/accounts",
//			HttpMethod.POST
//			mockAccount,
//			AccountsEntity::class.java
//		)
//		assertEquals(HttpStatus.OK,response.statusCode)
//		return response.body!!

		val account = AccountsEntity()
		account.accountNumber = ""
		account.userId = 1
		account.balance = BigDecimal.ZERO
		account.isActive = true
		account.name = "Saving Account"

		accountsRepository.save(account)

		return account
	}

	@Test
	fun `Calling Accounts should return 0 when to account in system`(){
		val token = createToken()
		val headers = HttpHeaders(
			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
		)
		val requestEntity = HttpEntity<String>(headers)

		val result = restTemplate.exchange(
			"/accounts/v1/list",
			HttpMethod.GET,
			requestEntity,
			List::class.java
		)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(0,result.body!!.size)
	}

	@Test
	fun `Calling Accounts should return 1 when to account in system`(){
		val token = createToken()
		var mock = createAccount()
		val headers = HttpHeaders(
			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
		)
		val requestEntity = HttpEntity<String>(headers)

		val result = restTemplate.exchange(
			"/accounts/v1/list",
			HttpMethod.GET,
			requestEntity,
			List::class.java
		)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals(1,result.body!!.size)
	}

	//@Test
	//fun createAccount
	//val token = createToken()
	//val headers = HttpHeaders(
//			MultiValueMap.fromSingleValue(mapOf("Authorization" to "Bearer $token"))
//		)
//		val mockAccount = AccRequest(
//			userId = 1,
//			name = "Debit Account",
//				balance = BigDecimal(1200.0),
//		)
//		val response = restTemplate.exchange(
//			"/accounts/v1/accounts",
//			HttpMethod.POST
//			mockAccount,
//			AccountsEntity::class.java
//		)
//		assertEquals(HttpStatus.OK,response.statusCode)
//		return response.body!!
}
