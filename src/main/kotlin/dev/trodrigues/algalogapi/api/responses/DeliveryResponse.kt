package dev.trodrigues.algalogapi.api.responses

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DeliveryResponse(
    val id: UUID,
    val clientName: String,
    val recipient: DeliveryRecipientResponse,
    val fee: BigDecimal,
    val status: String,
    val orderDate: LocalDateTime?,
    val endDate: LocalDateTime?
)

data class DeliveryRecipientResponse(
    val name: String,
    val street: String,
    val number: String,
    val neighborhood: String
)
