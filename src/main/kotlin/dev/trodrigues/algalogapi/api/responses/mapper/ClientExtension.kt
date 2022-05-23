package dev.trodrigues.algalogapi.api.responses.mapper

import dev.trodrigues.algalogapi.api.responses.ClientResponse
import dev.trodrigues.algalogapi.domain.entities.Client

fun Client.toResponse(): ClientResponse = ClientResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    phoneNumber = this.phoneNumber
)
