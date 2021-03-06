package com.gb.cashback.service

import com.gb.cashback.service.impl.CashbackIntegrationServiceImpl
import com.gb.cashback.service.impl.CashbackServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
@AutoConfigureMockMvc
class CashbackServiceImplTest {

    @MockBean
    private val cashbackIntegrationServiceImpl: CashbackIntegrationServiceImpl? = null

    @Autowired
    private val cashbackService: CashbackServiceImpl? = null

    @Test
    fun findCashbackTest() {
        BDDMockito.given<Long>(cashbackIntegrationServiceImpl?.getCashbackIntegration("45474132824"))
                .willReturn(200)

        val response = cashbackService!!.findCashback("45474132824")
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.credit, 200)
    }
}