package dev.trodrigues.algalogapi.domain.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_occurrence")
data class Occurrence(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @ManyToOne
    val delivery: Delivery,
    val description: String,
    val occurrenceDate: LocalDateTime? = null
)
