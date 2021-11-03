package com.gb.cashback.service.impl

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.repository.PurchaseRepository
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.PurchaseService
import com.gb.cashback.util.APPUtil
import com.gb.cashback.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@Service
class PurchaseServiceImpl(
        private val purchaseRepository: PurchaseRepository,
        private val resellerRepository: ResellerRepository
): PurchaseService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createPurchase(purchaseEntity: PurchaseEntity, resellerDocument: String): PurchaseDTO {
        log.info("Create Purchase service. purchaseCode={}", purchaseEntity.purchaseCode)

        val purchase = purchaseRepository.findByPurchaseCode(purchaseEntity.purchaseCode)
        if (purchase.isPresent) throw EntityExistsException(APIConstant.ERROR_400_PURCHASE)

        val document = APPUtil.removeSpecialCaracters(resellerDocument)
        val reseller = resellerRepository.findByResellerDocument(document)
                .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404_RESELER) }

        purchaseEntity.resellerEntity = reseller

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