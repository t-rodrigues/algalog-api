package dev.trodrigues.algalogapi.api.responses

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ClientResponse(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val phoneNumber: String
)
