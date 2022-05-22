package dev.trodrigues.algalogapi.api.exceptions

import dev.trodrigues.algalogapi.api.responses.ErrorResponse
import dev.trodrigues.algalogapi.api.responses.FieldErrorResponse
import dev.trodrigues.algalogapi.domain.services.exceptions.BusinessException
import dev.trodrigues.algalogapi.domain.services.exceptions.NotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        ex: NotFoundException
    ): ResponseEntity<Any> =
        errorResponse(HttpStatus.NOT_FOUND, ex.message)

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(
        ex: BusinessException
    ): ResponseEntity<Any> =
        errorResponse(HttpStatus.BAD_REQUEST, ex.message)

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val fields = ex.fieldErrors
        return errorResponse(HttpStatus.BAD_REQUEST, "Validation error", fields)
    }

    private fun errorResponse(
        status: HttpStatus,
        error: String,
        validationError: List<FieldError>? = null
    ): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
            status = status.value(),
            error = error,
            validationErrors = validationError?.map { FieldErrorResponse(it.field, it.defaultMessage) })
        return ResponseEntity.status(status).body(errorResponse)
    }

}
