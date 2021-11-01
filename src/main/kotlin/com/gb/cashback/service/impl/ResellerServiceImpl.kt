package com.gb.cashback.service.impl

import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.ResellerEntity
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.ResellerService
import com.gb.cashback.util.APPUtil
import com.gb.cashback.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@Service
class ResellerServiceImpl(
        private val resellerRepository: ResellerRepository
) : ResellerService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createReseller(resellerEntity: ResellerEntity): ResellerDTO {
        log.info("Create Reseller service. userName={}", resellerEntity.resellerFullName)

        resellerEntity.resellerEmail = APPUtil.normalizeFields(resellerEntity.resellerEmail)
        resellerEntity.resellerDocument = APPUtil.normalizeFields(resellerEntity.resellerDocument)

        val reseller = isExistReseller(resellerEntity.resellerEmail, resellerEntity.resellerDocument)
        if (reseller.isPresent) throw EntityExistsException("Reseller exists")

        resellerEntity.resellerPassword = APPUtil.encryptPassword(resellerEntity.resellerPassword)!!

        return resellerRepository.save(resellerEntity).toDTO()

    }

    override fun findReseller(resellerID: Long): ResellerDTO {
        log.info("Find Reseller service. resellerID={}", resellerID)

        val resellerDB = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException("Reseller not Exists") }

        return resellerDB.toDTO()
    }

    override fun updateReseller(resellerID: Long, resellerEntity: ResellerEntity): ResellerDTO {
        log.info("Update Reseller service. resellerName={}", resellerEntity.resellerFullName)

        val resellerDB = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException("Reseller not Exists") }

        val reseller = isExistReseller(resellerDB.resellerEmail, resellerDB.resellerDocument)

        if (reseller.isPresent && reseller.get().resellerId == resellerDB.resellerId) {
            updateFieldsReseller(resellerDB, resellerEntity)
        } else {
            throw EntityExistsException("Email or document already exists in the system")
        }

        return resellerRepository.save(resellerDB).toDTO()
    }

    override fun deleteReseller(resellerID: Long) {
        log.info("Delete Reseller service. resellerID={}", resellerID)

        val resellerDB = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException("Reseller not Exists") }

        resellerRepository.delete(resellerDB)
    }

    private fun isExistReseller(email: String, document: String): Optional<ResellerEntity> {
        return resellerRepository.findByResellerEmailOrResellerDocument(email, document)
    }

    private fun updateFieldsReseller(resellerDB: ResellerEntity, resellerEntity: ResellerEntity) {
        resellerDB.resellerFullName = resellerEntity.resellerFullName
        resellerDB.resellerDocument = APPUtil.normalizeFields(resellerEntity.resellerDocument)
        resellerDB.resellerEmail = APPUtil.normalizeFields(resellerEntity.resellerEmail)
        resellerDB.resellerPassword = APPUtil.encryptPassword(resellerEntity.resellerPassword)!!
    }
}