package dev.trodrigues.algalogapi.domain.services

import dev.trodrigues.algalogapi.api.requests.ClientRequest
import dev.trodrigues.algalogapi.domain.entities.Client
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ClientCatalogService {

    fun existsByEmail(email: String): Boolean

    fun findById(clientId: UUID): Client

    fun findAll(pageable: Pageable): Page<Client>

    fun createClient(client: Client): Client

    fun updateClient(clientId: UUID, clientRequest: ClientRequest): Client

    fun deleteById(clientId: UUID)

}
