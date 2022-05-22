package dev.trodrigues.algalogapi.api.exceptions

import dev.trodrigues.algalogapi.api.responses.ErrorResponse
import dev.trodrigues.algalogapi.api.responses.FieldErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val fields = ex.fieldErrors.map { FieldErrorResponse(it.field, it.defaultMessage) }
        return handleExceptionInternal(
            ex,
            ErrorResponse(status = status.value(), error = "Validation Error", validationErrors = fields),
            headers,
            status,
            request
        )
    }

}
