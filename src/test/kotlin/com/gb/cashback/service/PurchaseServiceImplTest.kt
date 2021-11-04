package com.gb.cashback.service

import com.gb.cashback.constant.StatusPurchaseEnum
import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.entity.CashbackEntity
import com.gb.cashback.entity.DocumentExceptionEntity
import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.entity.ResellerEntity
import com.gb.cashback.repository.CashbackRepository
import com.gb.cashback.repository.DocumentExceptionRepository
import com.gb.cashback.repository.PurchaseRepository
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.impl.PurchaseServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseServiceImplTest {

    @MockBean
    private val purchaseRepository: PurchaseRepository? = null

    @MockBean
    private val resellerRepository: ResellerRepository? = null

    @MockBean
    private val documentExceptionRepository: DocumentExceptionRepository? = null

    @MockBean
    private val cashbackRepository: CashbackRepository? = null

    @Autowired
    private val purchaseService: PurchaseServiceImpl? = null

    @Test
    fun createPurchaseWithDocumentInValidationSuccessTest() {
        val document = "45478963258"

        BDDMockito.given<Optional<PurchaseEntity>>(purchaseRepository?.findByPurchaseCode(anyObject()))
                .willReturn(Optional.empty())

        BDDMockito.given<PurchaseEntity>(purchaseRepository?.save(anyObject()))
                .willReturn(getPurchase())

        BDDMockito.given<Optional<ResellerEntity>>(resellerRepository?.findByResellerDocument(document))
                .willReturn(Optional.of(getReseller()))

        BDDMockito.given<List<DocumentExceptionEntity>>(documentExceptionRepository?.findAll())
                .willReturn(listOf())

        BDDMockito.given<List<CashbackEntity>>(cashbackRepository?.findAll())
                .willReturn(getCashbackList())

        val response = purchaseService!!.createPurchase(getPurchase(), document)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.purchaseId, 1L)
        Assertions.assertEquals(response.purchaseStatus, StatusPurchaseEnum.IN_VALIDATION.value)
    }

    @Test
    fun createPurchaseWithDocumentApprovedSuccessTest() {
        val document = "45474132822"

        BDDMockito.given<Optional<PurchaseEntity>>(purchaseRepository?.findByPurchaseCode(anyObject()))
                .willReturn(Optional.empty())

        BDDMockito.given<PurchaseEntity>(purchaseRepository?.save(anyObject()))
                .willReturn(getPurchaseTwo())

        BDDMockito.given<Optional<ResellerEntity>>(resellerRepository?.findByResellerDocument(document))
                .willReturn(Optional.of(getResellerTwo()))

        BDDMockito.given<List<DocumentExceptionEntity>>(documentExceptionRepository?.findAll())
                .willReturn(listOf(getDocumentException()))

        BDDMockito.given<List<CashbackEntity>>(cashbackRepository?.findAll())
                .willReturn(getCashbackList())

        val response = purchaseService!!.createPurchase(getPurchaseTwo(), document)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.purchaseId, 2L)
        Assertions.assertEquals(response.purchaseStatus, StatusPurchaseEnum.APPROVED.value)
    }

    @Test
    fun findPurchaseByResellerSuccessTest() {
        BDDMockito.given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.of(getReseller()))

        BDDMockito.given<Page<PurchaseEntity>>(purchaseRepository?.findAllByResellerEntity(anyObject(), anyObject()))
                .willReturn(PageImpl(getPurchaseList()))

        val response = purchaseService!!.findPurchaseByReseller(PageRequest.of(0, 10), 1L)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.content[0].purchaseId, 1L)
    }

    @Test
    fun findAllPurchaseSuccessTest() {
        BDDMockito.given<Page<PurchaseEntity>>(purchaseRepository?.findAll(PageRequest.of(0, 10)))
                .willReturn(PageImpl(getPurchaseList()))

        val response = purchaseService!!.findAllPurchase(PageRequest.of(0, 10))
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.content[0].purchaseId, 1L)
    }

    private fun getPurchase(): PurchaseEntity {
        return PurchaseEntity(
            1L,
            "12345",
            BigDecimal(100),
            LocalDate.now(),
            StatusPurchaseEnum.IN_VALIDATION,
            BigDecimal(10),
            10,
            getReseller()
        )
    }

    private fun getPurchaseTwo(): PurchaseEntity {
        return PurchaseEntity(
                2L,
                "123456",
                BigDecimal(100),
                LocalDate.now(),
                StatusPurchaseEnum.APPROVED,
                BigDecimal(10),
                10,
                getResellerTwo()
        )
    }

    private fun getPurchaseList(): List<PurchaseEntity> {
        return listOf(PurchaseEntity(
                1L,
                "12345",
                BigDecimal(100),
                LocalDate.now(),
                StatusPurchaseEnum.APPROVED,
                BigDecimal(10),
                10,
                getReseller()
        ))
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

    private fun getResellerTwo(): ResellerEntity {
        return ResellerEntity(
                2L,
                "Diego Candido",
                "45474132822",
                "diego2@email.com",
                "abcd1234",
        )
    }

    private fun getCashbackList(): List<CashbackEntity> {
        return listOf(CashbackEntity(
            1L,
            BigDecimal(0),
            BigDecimal(1000),
            10
        ))
    }

    private fun getDocumentException(): DocumentExceptionEntity {
        return DocumentExceptionEntity(
            1L,
            "45474132822"
        )
    }


    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}