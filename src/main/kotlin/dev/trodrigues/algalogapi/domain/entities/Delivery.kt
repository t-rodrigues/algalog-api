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
    val id: UUID? = null,
    @ManyToOne
    val client: Client,
    @Embedded
    val recipient: Recipient,
    val fee: BigDecimal,
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    val occurrences: List<Occurrence>? = mutableListOf(),
    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus? = null,
    var orderDate: LocalDateTime? = null,
    var endDate: LocalDateTime? = null
)
