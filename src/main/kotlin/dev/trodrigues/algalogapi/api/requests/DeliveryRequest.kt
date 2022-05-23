package dev.trodrigues.algalogapi.api.requests

import dev.trodrigues.algalogapi.domain.entities.Client
import dev.trodrigues.algalogapi.domain.entities.Delivery
import dev.trodrigues.algalogapi.domain.entities.Recipient
import java.math.BigDecimal
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

data class DeliveryRequest(
    @Valid
    val client: DeliveryClientRequest,
    @Valid
    val recipient: DeliveryRecipientRequest,
    val fee: BigDecimal,
)

data class DeliveryClientRequest(
    @field:NotBlank
    val id: UUID?
)

data class DeliveryRecipientRequest(
    @field:NotBlank
    val name: String?,
    @field:NotBlank
    val street: String?,
    @field:NotBlank
    val number: String?,
    @field:NotBlank
    val neighborhood: String?
)

fun DeliveryRequest.toEntity(client: Client): Delivery = Delivery(
    client = client,
    recipient = this.recipient.toEntity(),
    fee = this.fee
)

fun DeliveryRecipientRequest.toEntity(): Recipient = Recipient(
    name = this.name!!,
    street = this.street!!,
    number = this.number!!,
    neighborhood = this.neighborhood!!
)
