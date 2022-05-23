package dev.trodrigues.algalogapi.infra.repositories

import dev.trodrigues.algalogapi.domain.entities.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeliveryRepository : JpaRepository<Delivery, UUID>
