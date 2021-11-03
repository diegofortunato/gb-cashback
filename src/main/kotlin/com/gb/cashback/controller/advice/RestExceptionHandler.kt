package com.gb.cashback.controller.advice

import com.gb.cashback.constant.APIConstant
import com.gb.cashback.controller.response.ErrorResponse
import com.gb.cashback.controller.response.Response
import com.gb.cashback.exception.AuthException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.Instant
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class RestExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    private val messageSource: MessageSource? = null

    @ExceptionHandler(value = [(EntityExistsException::class)])
    fun handleEntityExistsException(ex: EntityExistsException, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleEntityExistsException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                APIConstant.ERROR_400,
                ex.message.toString()
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(AuthException::class)])
    fun handleAuthException(ex: AuthException, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleAuthException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                APIConstant.ERROR_AUTH_400,
                APIConstant.DETAILS_ERROR_AUTH_400
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(EntityNotFoundException::class)])
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleEntityNotFoundException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                APIConstant.ERROR_404,
                ex.message.toString()
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(MethodArgumentNotValidException::class)])
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleMethodArgumentNotValidException: {}", ex.message)

        val errors = arrayListOf<String>()

        val fieldErrors = ex.bindingResult.fieldErrors
        fieldErrors.forEach { field ->
            val message = messageSource!!.getMessage(field, LocaleContextHolder.getLocale())
            errors.add(message)
        }

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.PRECONDITION_FAILED.value(),
                APIConstant.ERROR_412,
                errors.toString(),
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.PRECONDITION_FAILED)
    }

    @ExceptionHandler(value = [(HttpMessageNotReadableException::class)])
    fun handleMethodHttpMessageNotReadableException(ex: HttpMessageNotReadableException, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleMethodHttpMessageNotReadableException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.PRECONDITION_FAILED.value(),
                APIConstant.ERROR_412,
                ex.mostSpecificCause.message.toString(),
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.PRECONDITION_FAILED)
    }

    @ExceptionHandler(value = [(Exception::class)])
    fun handleException(ex: Exception, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                APIConstant.ERROR_500,
                APIConstant.DETAILS_ERROR_500
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}