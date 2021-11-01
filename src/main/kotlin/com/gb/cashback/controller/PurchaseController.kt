package com.gb.cashback.controller

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.controller.request.Request
import com.gb.cashback.controller.response.Response
import com.gb.cashback.dto.PurchaseDTO
import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.service.PurchaseService
import com.gb.cashback.util.extension.DTOTOEntityExtension.toEntity
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URLEncoder
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class PurchaseController(private val purchaseService: PurchaseService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.SERVICE_POST_PURCHASE)
    fun createPurchase(@Valid @RequestBody purchaseRequest: Request<PurchaseDTO>): ResponseEntity<Response<PurchaseDTO>> {
        log.info("POST ${APIConstant.SERVICE_POST_PURCHASE} for purchase {}", purchaseRequest.request)

        val purchase = purchaseService.createPurchase(purchaseRequest.request.toEntity())
        return ResponseEntity
                .created(URI.create(
                        URLEncoder.encode(APIConstant.BASE_API + APIConstant.SERVICE_GET_PURCHASE, "UTF-8")))
                .body(Response(data = purchase))
    }

    @GetMapping(APIConstant.SERVICE_GET_PURCHASE)
    fun findPurchases(): ResponseEntity<Response<List<PurchaseDTO>>> {
        log.info("GET ${APIConstant.SERVICE_GET_PURCHASE}")

        val purchases = purchaseService.findPurchases()
        return ResponseEntity.ok(Response(data = purchases))
    }
}