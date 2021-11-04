package com.gb.cashback.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import java.time.LocalDate

@AutoConfigureMockMvc
class APPUtilTest {

    @Test
    fun normalizeFieldsTest() {
        val result = APPUtil.normalizeFields("DIEGO@GMAIL.COM")
        Assertions.assertEquals(result, "diego@gmail.com")
    }

    @Test
    fun removeSpecialCaractersTest() {
        val result = APPUtil.removeSpecialCaracters("454.741.328-22")
        Assertions.assertEquals(result, "45474132822")
    }

    @Test
    fun encryptPasswordTest() {
        val result = APPUtil.encryptPassword("123456")
        Assertions.assertNotEquals(result, "123456")
    }

    @Test
    fun verifyPasswordIsCorrectTest() {
        val storedPassword = APPUtil.encryptPassword("123456")
        val result = APPUtil.verifyPasswordIsCorrect("123456", storedPassword!!)
        Assertions.assertEquals(result, true)
    }

    @Test
    fun stringToDateTest() {
        val result = APPUtil.stringToDate(LocalDate.now().toString())
        Assertions.assertNotEquals(result.compareTo(LocalDate.now()), true)
    }

}