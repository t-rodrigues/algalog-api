package dev.trodrigues.algalogapi.api.requests

import javax.validation.constraints.NotBlank

data class OccurrenceRequest(
    @field:NotBlank
    val description: String
)
