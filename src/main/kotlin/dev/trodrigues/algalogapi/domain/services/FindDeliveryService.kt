package dev.trodrigues.algalogapi.domain.services

import dev.trodrigues.algalogapi.domain.entities.Delivery
import java.util.*

interface FindDeliveryService {

    fun findDeliveryById(deliveryId: UUID): Delivery

}
