package com.gb.cashback.service.impl

import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.entity.PurchaseEntity
import com.gb.cashback.repository.PurchaseRepository
import com.gb.cashback.service.PurchaseService
import com.gb.cashback.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class PurchaseServiceImpl(private val purchaseRepository: PurchaseRepository): PurchaseService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createPurchase(purchaseEntity: PurchaseEntity): PurchaseDTO {
        log.info("Create Purchase service. purchaseCode={}", purchaseEntity.purchaseCode)

        return purchaseRepository.save(purchaseEntity).toDTO()
    }

    override fun findPurchases(): List<PurchaseDTO> {
        log.info("Find Purchase service")

        return purchaseRepository
                .findAll()
                .stream()
                .map { purchases -> purchases.toDTO() }
                .collect(Collectors.toList())
    }
}