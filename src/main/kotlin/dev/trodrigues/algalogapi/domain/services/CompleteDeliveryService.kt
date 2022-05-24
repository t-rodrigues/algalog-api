package dev.trodrigues.algalogapi.domain.services

import java.util.*

interface CompleteDeliveryService {

    fun finish(deliveryId: UUID)

}
