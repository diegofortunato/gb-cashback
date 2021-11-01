package com.gb.cashback.service

import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.repository.PurchaseRepository
import com.gb.cashback.service.impl.PurchaseServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseServiceImplTest {

    @MockBean
    private val purchaseRepository: PurchaseRepository? = null

    @Autowired
    private val purchaseService: PurchaseServiceImpl? = null

    @Test
    fun createPurchaseWithSuccessTest() {
        BDDMockito.given<PurchaseEntity>(purchaseRepository?.save(anyObject()))
                .willReturn(getPurchase())

        val response = purchaseService!!.createPurchase(getPurchase())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.purchaseId, 1L)
    }

    @Test
    fun findPurchaseWithSuccessTest() {
        BDDMockito.given<List<PurchaseEntity>>(purchaseRepository?.findAll())
                .willReturn(getPurchaseList())

        val response = purchaseService!!.findPurchases()
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response[0].purchaseId, 1L)
    }

    private fun getPurchase(): PurchaseEntity {
        return PurchaseEntity(
            1L,
            "12345",
            50.00,
            "01/11/2021",
            "454.741.328-24",
        )
    }

    private fun getPurchaseList(): List<PurchaseEntity> {
        return listOf(PurchaseEntity(
                1L,
                "12345",
                50.00,
                "01/11/2021",
                "454.741.328-24",
        ))
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}