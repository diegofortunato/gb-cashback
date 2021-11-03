package com.gb.cashback.util

import org.mindrot.jbcrypt.BCrypt
import org.slf4j.LoggerFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object APPUtil {

    private val log = LoggerFactory.getLogger(javaClass)

    @JvmStatic
    fun normalizeFields(fieldName: String): String {
        log.info("Normalize field={}", fieldName)

        return fieldName.lowercase()
    }

    @JvmStatic
    fun removeSpecialCaracters(field: String): String {
        log.info("Remove special caracters for document")

        return field.replace("""[.-]""".toRegex(), "")
    }

    @JvmStatic
    fun encryptPassword(password: String): String? {
        log.info("Init encrypt Password")

        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    @JvmStatic
    fun verifyPasswordIsCorrect(password: String, storedPassword: String): Boolean {
        log.info("Init verify Password is correct")

        val result = BCrypt.hashpw(password, storedPassword)
        return result.equals(storedPassword)
    }

    @JvmStatic
    fun stringToDate(date: String): LocalDate {
        log.info("Init string to date method")

        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}