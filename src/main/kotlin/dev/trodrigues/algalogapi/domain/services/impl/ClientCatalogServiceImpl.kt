package dev.trodrigues.algalogapi.domain.services.impl

import dev.trodrigues.algalogapi.api.requests.ClientRequest
import dev.trodrigues.algalogapi.api.requests.toEntity
import dev.trodrigues.algalogapi.domain.entities.Client
import dev.trodrigues.algalogapi.domain.services.ClientCatalogService
import dev.trodrigues.algalogapi.domain.services.exceptions.BusinessException
import dev.trodrigues.algalogapi.domain.services.exceptions.NotFoundException
import dev.trodrigues.algalogapi.infra.repositories.ClientRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ClientCatalogServiceImpl(
    private val clientRepository: ClientRepository
) : ClientCatalogService {
    override fun existsByEmail(email: String): Boolean =
        clientRepository.existsByEmail(email)

    override fun findById(clientId: UUID): Client =
        clientRepository.findById(clientId).orElseThrow { NotFoundException("client not found with: $clientId") }

    override fun findAll(pageable: Pageable): Page<Client> =
        clientRepository.findAll(pageable)

    @Transactional
    override fun createClient(client: Client): Client {
        checkEmailAlreadyInUse(client.email)
        return clientRepository.save(client)
    }

    @Transactional
    override fun updateClient(clientId: UUID, clientRequest: ClientRequest): Client {
        val oldClient = findById(clientId)
        if (clientRequest.email != oldClient.email) {
            checkEmailAlreadyInUse(clientRequest.email!!)
        }
        return clientRepository.save(clientRequest.toEntity(oldClient))
    }

    @Transactional
    override fun deleteById(clientId: UUID) {
        val client = findById(clientId)
        clientRepository.delete(client)
    }

    private fun checkEmailAlreadyInUse(email: String) {
        if (existsByEmail(email)) {
            throw BusinessException("email already taken")
        }
    }

}
