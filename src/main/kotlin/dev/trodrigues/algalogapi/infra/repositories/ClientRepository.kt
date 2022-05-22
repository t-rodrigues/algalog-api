package dev.trodrigues.algalogapi.infra.repositories

import dev.trodrigues.algalogapi.domain.entities.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClientRepository : JpaRepository<Client, UUID>
