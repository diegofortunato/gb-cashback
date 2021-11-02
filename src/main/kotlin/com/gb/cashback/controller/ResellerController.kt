package com.gb.cashback.controller

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.controller.request.Request
import com.gb.cashback.controller.response.Response
import com.gb.cashback.controller.response.ResponsePagination
import com.gb.cashback.dto.ResellerDTO
import com.gb.cashback.service.ResellerService
import com.gb.cashback.util.extension.DTOTOEntityExtension.toEntity
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URLEncoder
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class ResellerController(private val resellerService: ResellerService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.SERVICE_CREATE_RESELLER)
    fun createReseller(@Valid @RequestBody resellerRequest: Request<ResellerDTO>): ResponseEntity<Response<ResellerDTO>> {
        log.info("POST ${APIConstant.SERVICE_CREATE_RESELLER} for reseller {}", resellerRequest.request.resellerFullName)

        val reseller = resellerService.createReseller(resellerRequest.request.toEntity())
        return ResponseEntity
                .created(URI.create(
                        URLEncoder.encode(APIConstant.BASE_API + APIConstant.SERVICE_GET_RESELLER, "UTF-8")))
                .body(Response(data = reseller))
    }

    @GetMapping(APIConstant.SERVICE_GET_RESELLER)
    fun findReseller(@PathVariable("id") resellerID: Long): ResponseEntity<Response<ResellerDTO>> {
        log.info("GET ${APIConstant.SERVICE_GET_RESELLER} for ID {}", resellerID)

        val reseller = resellerService.findReseller(resellerID)
        return ResponseEntity.ok(Response(data = reseller))
    }

    @GetMapping(APIConstant.SERVICE_GET_ALL_RESELLER)
    fun findAllReseller(
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int
    ): ResponseEntity<ResponsePagination<List<ResellerDTO>>> {
        log.info("GET ALL ${APIConstant.SERVICE_GET_ALL_RESELLER}")

        val paging = PageRequest.of(page, size)

        val response = resellerService.findAllReseller(paging)
        return ResponseEntity.ok(
                ResponsePagination(
                    data = response.content,
                    totalPages = response.totalPages,
                    currentPage = response.number,
                    totalItems = response.totalElements
            )
        )
    }

    @PatchMapping(APIConstant.SERVICE_UPDATE_RESELLER)
    fun updateReseller(@PathVariable("id") resellerID: Long, @Valid @RequestBody resellerRequest: Request<ResellerDTO>):
            ResponseEntity<Response<ResellerDTO>> {
        log.info("PATCH ${APIConstant.SERVICE_UPDATE_RESELLER} for ID {}", resellerRequest.request.resellerId)

        val reseller = resellerService.updateReseller(resellerID, resellerRequest.request.toEntity())
        return ResponseEntity.ok(Response(data = reseller))
    }

    @DeleteMapping(APIConstant.SERVICE_DELETE_RESELLER)
    fun deleteReseller(@PathVariable("id") resellerID: Long): ResponseEntity<Response<ResellerDTO>> {
        log.info("DELETE ${APIConstant.SERVICE_DELETE_RESELLER} for ID {}", resellerID)

        resellerService.deleteReseller(resellerID)
        return ResponseEntity.noContent().build()
    }
}