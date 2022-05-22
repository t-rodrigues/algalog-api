package dev.trodrigues.algalogapi.domain.entities

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Recipient(
    @Column(name = "recipient_name")
    val name: String,
    @Column(name = "recipient_street")
    val street: String,
    @Column(name = "recipient_number")
    val number: String,
    @Column(name = "recipient_neighborhood")
    val neighborhood: String
)
