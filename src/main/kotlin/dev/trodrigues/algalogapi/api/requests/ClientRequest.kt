package dev.trodrigues.algalogapi.api.requests

import dev.trodrigues.algalogapi.domain.entities.Client
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ClientRequest(
    @field:NotBlank
    @field:Size(min = 3, max = 150)
    val name: String?,
    @field:NotBlank
    @field:Email
    @field:Size(max = 255)
    val email: String?,
    @field:NotBlank
    @field:Size(max = 20)
    val phoneNumber: String?
)

fun ClientRequest.toEntity(): Client = Client(
    name = this.name!!,
    email = this.email!!,
    phoneNumber = this.phoneNumber!!
)

fun ClientRequest.toEntity(client: Client) = Client(
    id = client.id,
    name = this.name ?: client.name,
    email = this.email ?: client.email,
    phoneNumber = this.phoneNumber ?: client.phoneNumber
)
