package com.gb.cashback.util

import org.mindrot.jbcrypt.BCrypt
import org.slf4j.LoggerFactory
import java.text.Normalizer

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
}