package dev.trodrigues.algalogapi.api.responses.mapper

import dev.trodrigues.algalogapi.api.responses.DeliveryRecipientResponse
import dev.trodrigues.algalogapi.api.responses.DeliveryResponse
import dev.trodrigues.algalogapi.domain.entities.Delivery
import dev.trodrigues.algalogapi.domain.entities.Recipient

fun Delivery.toResponse(): DeliveryResponse = DeliveryResponse(
    id = this.id!!,
    clientName = this.client.name,
    recipient = this.recipient.toResponse(),
    fee = this.fee,
    status = this.status!!.name,
    orderDate = this.orderDate,
    endDate = this.endDate
)

fun Recipient.toResponse(): DeliveryRecipientResponse = DeliveryRecipientResponse(
    name = this.name,
    street = this.street,
    number = this.number,
    neighborhood = this.neighborhood
)
