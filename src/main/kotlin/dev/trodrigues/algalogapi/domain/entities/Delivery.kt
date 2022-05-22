package dev.trodrigues.algalogapi.domain.entities

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_delivery")
@Access(AccessType.FIELD)
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    @ManyToOne
    val client: Client,
    @Embedded
    val recipient: Recipient,
    val fee: BigDecimal,
    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus,
    val orderDate: LocalDateTime,
    val endDate: LocalDateTime
)
