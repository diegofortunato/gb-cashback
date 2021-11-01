package com.gb.cashback.service

import com.gb.cashback.entity.ResellerEntity
import com.gb.cashback.exception.AuthException
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.impl.AuthServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class AuthServiceImplTest {

    @MockBean
    private val resellerRepository: ResellerRepository? = null

    @Autowired
    private val authService: AuthServiceImpl? = null

    @Test
    fun findResellerLoginWithSuccessTest() {
        BDDMockito.given<Optional<ResellerEntity>>(
                resellerRepository?.findByResellerEmailAndResellerPassword("diego.fcandido1996@gmail.com", "abcd1234"))
                .willReturn(Optional.of(getReseller()))

        authService!!.login("diego.fcandido1996@gmail.com", "abcd1234")
        Mockito.verify(resellerRepository, Mockito.times(1))!!.findByResellerEmailAndResellerPassword("diego.fcandido1996@gmail.com", "abcd1234")
    }

    @Test
    fun findResellerLoginWithFailedTest() {
        BDDMockito.given<Optional<ResellerEntity>>(resellerRepository?.findByResellerEmailOrResellerDocument("diego.fcandido1996@gmail.com", "abcd"))
                .willReturn(Optional.empty())

        Assertions.assertThrows(
                AuthException::class.java
        ) { authService!!.login("diego.fcandido1996@gmail.com", "abcd") }
    }

    private fun getReseller(): ResellerEntity {
        return ResellerEntity(
                1L,
                "Diego Fortunato",
                "45478963258",
                "diego@email.com",
                "abcd1234",
        )
    }
}