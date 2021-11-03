package com.gb.cashback.service.impl

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.constant.StatusPurchaseEnum
import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.repository.CashbackRepository
import com.gb.cashback.repository.DocumentExceptionRepository
import com.gb.cashback.repository.PurchaseRepository
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.PurchaseService
import com.gb.cashback.util.APPUtil
import com.gb.cashback.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.stream.Collectors
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@Service
class PurchaseServiceImpl(
        private val purchaseRepository: PurchaseRepository,
        private val resellerRepository: ResellerRepository,
        private val documentExceptionRepository: DocumentExceptionRepository,
        private val cashbackRepository: CashbackRepository
): PurchaseService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createPurchase(purchaseEntity: PurchaseEntity, resellerDocument: String): PurchaseDTO {
        log.info("Create Purchase service. purchaseCode={}", purchaseEntity.purchaseCode)

        val purchase = purchaseRepository.findByPurchaseCode(purchaseEntity.purchaseCode)
        if (purchase.isPresent) throw EntityExistsException(APIConstant.ERROR_400_PURCHASE)

        val document = APPUtil.removeSpecialCaracters(resellerDocument)
        val reseller = resellerRepository.findByResellerDocument(document)
                .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404_RESELER) }

        val documents = documentExceptionRepository.findAll()
        var purchaseStatus = StatusPurchaseEnum.IN_VALIDATION

        documents.forEach { documentException ->
            if(documentException.document == document) {
                purchaseStatus = StatusPurchaseEnum.APPROVED
            }
        }

        val valuePurchase = purchaseEntity.purchaseValue
        var percentageCashbackPurchage = 0
        var valueCashbackPurchage = BigDecimal("0")

        val cashbacks = cashbackRepository.findAll()
        cashbacks.forEach { cashback ->
            if (valuePurchase >= cashback.minValueCashback && valuePurchase <= cashback.maxValueCashback){
                percentageCashbackPurchage = cashback.percentage
                val value = valuePurchase.toDouble() * cashback.percentage / 100
                valueCashbackPurchage = BigDecimal(value)
            }
        }

        purchaseEntity.resellerEntity = reseller
        purchaseEntity.purchaseStatus = purchaseStatus
        purchaseEntity.purchaseValueCashback = valueCashbackPurchage
        purchaseEntity.purchasePercentage = percentageCashbackPurchage

        return purchaseRepository.save(purchaseEntity).toDTO()
    }

    override fun findAllPurchase(paging: PageRequest): Page<PurchaseDTO> {
        log.info("Find all Purchase service")

        return purchaseRepository.findAll(paging).map { purchase -> purchase.toDTO() }
    }

    override fun findPurchaseByReseller(paging: PageRequest, resellerID: Long): Page<PurchaseDTO> {
        log.info("Find all Purchase service")

        val reseller = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404_RESELER) }

        return purchaseRepository.findAllByResellerEntity(paging, reseller).map { purchase -> purchase.toDTO() }
    }
}