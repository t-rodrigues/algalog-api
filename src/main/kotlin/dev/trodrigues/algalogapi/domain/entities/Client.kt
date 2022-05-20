package dev.trodrigues.algalogapi.domain.entities

import java.util.UUID

data class Client(
    val id: UUID,
    val name: String,
    val email: String,
    val phoneNumber: String
)
