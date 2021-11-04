package com.gb.cashback.service

import com.gb.cashback.entity.ResellerEntity
import com.gb.cashback.repository.ResellerRepository
import com.gb.cashback.service.impl.ResellerServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.Optional
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@SpringBootTest
@AutoConfigureMockMvc
class ResellerServiceImplTest {

    @MockBean
    private val resellerRepository: ResellerRepository? = null

    @Autowired
    private val resellerService: ResellerServiceImpl? = null

    @Test
    fun createResellerWithSuccessTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findByResellerEmailOrResellerDocument(anyString(), anyString()))
                .willReturn(Optional.empty())

        given<ResellerEntity>(resellerRepository?.save(anyObject()))
                .willReturn(getReseller())

        val response = resellerService!!.createReseller(getReseller())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.resellerId, 1L)
    }

    @Test
    fun createResellerWithFailedTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findByResellerEmailOrResellerDocument(anyString(), anyString()))
                .willReturn(Optional.of(getReseller()))

        Assertions.assertThrows(
                EntityExistsException::class.java
        ) { resellerService!!.createReseller(getReseller()) }
    }

    @Test
    fun findResellerWithSuccessTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.of(getReseller()))

        val response = resellerService!!.findReseller(1L)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.resellerId, 1L)
    }

    @Test
    fun findResellerWithFailedTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.empty())

        Assertions.assertThrows(
                EntityNotFoundException::class.java
        ) { resellerService!!.findReseller(1L) }
    }

    @Test
    fun findAllResellerWithSuccessTest() {
        given<Page<ResellerEntity>>(resellerRepository?.findAll(PageRequest.of(0, 10)))
                .willReturn(PageImpl(getResellerList()))

        val response = resellerService!!.findAllReseller(PageRequest.of(0, 10))
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.size, 1)
    }

    @Test
    fun updateResellerWithSuccessTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.of(getReseller()))

        given<Optional<ResellerEntity>>(resellerRepository?.findByResellerDocument(anyString()))
                .willReturn(Optional.of(getReseller()))

        given<Optional<ResellerEntity>>(resellerRepository?.findByResellerEmail(anyString()))
                .willReturn(Optional.of(getReseller()))

        given<ResellerEntity>(resellerRepository?.save(anyObject()))
                .willReturn(getResellerUpdate())

        val response = resellerService!!.updateReseller(1L, getReseller())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.resellerFullName, "Diego Fortunato Candido")
    }

    @Test
    fun updateResellerNotFoundTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.empty())

        Assertions.assertThrows(
                EntityNotFoundException::class.java
        ) { resellerService!!.updateReseller(1L, getResellerUpdate()) }
    }

    @Test
    fun updateResellerExistsTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.of(getReseller()))

        given<Optional<ResellerEntity>>(resellerRepository?.findByResellerDocument(anyString()))
                .willReturn(Optional.of(getReseller()))

        given<Optional<ResellerEntity>>(resellerRepository?.findByResellerEmail(anyString()))
                .willReturn(Optional.of(getResellerTwo()))

        Assertions.assertThrows(
                EntityExistsException::class.java
        ) { resellerService!!.updateReseller(1L, getResellerUpdate()) }
    }

    @Test
    fun deleteResellerWithSuccessTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.of(getReseller()))

        doNothing().`when`(resellerRepository)!!.delete(getReseller())

        resellerService!!.deleteReseller(1L)

        verify(resellerRepository, times(1))!!.delete(getReseller())
    }

    @Test
    fun deleteResellerWithFailedTest() {
        given<Optional<ResellerEntity>>(resellerRepository?.findById(1L))
                .willReturn(Optional.empty())

        Assertions.assertThrows(
                EntityNotFoundException::class.java
        ) { resellerService!!.deleteReseller(1L) }
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
                "45478963252",
                "diego@email.com",
                "abcd1234",
        )
    }

    private fun getResellerList(): List<ResellerEntity> {
        return listOf(ResellerEntity(
                1L,
                "Diego Fortunato Candido",
                "45478963258",
                "diego@email.com",
                "abcd1234",
        ))
    }

    private fun getResellerUpdate(): ResellerEntity {
        return ResellerEntity(
            1L,
            "Diego Fortunato Candido",
            "45478963258",
            "diego@email.com",
            "abcd1234",
        )
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}