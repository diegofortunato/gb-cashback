package com.gb.cashback.service.impl

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.entity.ResellerEntity
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.ResellerService
import com.gb.cashback.util.APPUtil
import com.gb.cashback.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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
        resellerEntity.resellerDocument = APPUtil.removeSpecialCaracters(resellerEntity.resellerDocument)

        val reseller = isExistReseller(resellerEntity.resellerEmail, resellerEntity.resellerDocument)
        if (reseller.isPresent) throw EntityExistsException(APIConstant.ERROR_400_RESELER)

        resellerEntity.resellerPassword = APPUtil.encryptPassword(resellerEntity.resellerPassword)!!

        return resellerRepository.save(resellerEntity).toDTO()

    }

    override fun findReseller(resellerID: Long): ResellerDTO {
        log.info("Find Reseller service. resellerID={}", resellerID)

        val resellerDB = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404_RESELER) }

        return resellerDB.toDTO()
    }

    override fun findAllReseller(paging: PageRequest): Page<ResellerDTO> {
        log.info("Find All Reseller service.")

        return resellerRepository.findAll(paging).map { reseller -> reseller.toDTO() }
    }

    override fun updateReseller(resellerID: Long, resellerEntity: ResellerEntity): ResellerDTO {
        log.info("Update Reseller service. resellerName={}", resellerEntity.resellerFullName)

        val resellerDB = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404_RESELER) }

        val document = APPUtil.removeSpecialCaracters(resellerEntity.resellerDocument)

        val resellerDocument = isExistResellerDocument(document)
        val resellerEmail = isExistResellerEmail(resellerEntity.resellerEmail)

        if (resellerDocument.isPresent && resellerDocument.get().resellerId == resellerDB.resellerId &&
                resellerEmail.isPresent && resellerEmail.get().resellerId == resellerDB.resellerId) {
            updateFieldsReseller(resellerDB, resellerEntity)
        } else {
            throw EntityExistsException(APIConstant.ERROR_400_RESELER)
        }

        return resellerRepository.save(resellerDB).toDTO()
    }

    override fun deleteReseller(resellerID: Long) {
        log.info("Delete Reseller service. resellerID={}", resellerID)

        val resellerDB = resellerRepository.findById(resellerID)
                .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404_RESELER) }

        resellerRepository.delete(resellerDB)
    }

    private fun isExistReseller(resellerEmail: String, resellerDocument: String): Optional<ResellerEntity> {
        return resellerRepository.findByResellerEmailOrResellerDocument(resellerEmail, resellerDocument)
    }

    private fun isExistResellerEmail(email: String): Optional<ResellerEntity> {
        return resellerRepository.findByResellerEmail(email)
    }

    private fun isExistResellerDocument(document: String): Optional<ResellerEntity> {
        return resellerRepository.findByResellerDocument(document)
    }

    private fun updateFieldsReseller(resellerDB: ResellerEntity, resellerEntity: ResellerEntity) {
        resellerDB.resellerFullName = resellerEntity.resellerFullName
        resellerDB.resellerDocument = APPUtil.removeSpecialCaracters(resellerEntity.resellerDocument)
        resellerDB.resellerEmail = APPUtil.normalizeFields(resellerEntity.resellerEmail)
        resellerDB.resellerPassword = APPUtil.encryptPassword(resellerEntity.resellerPassword)!!
    }
}