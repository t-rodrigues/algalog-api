package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.domain.entities.Client
import dev.trodrigues.algalogapi.infra.repositories.ClientRepository
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/clients")
class ClientController(
    private val clientRepository: ClientRepository
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun list(): List<Client> = clientRepository.findAll()

    @GetMapping("/{clientId}")
    fun findByClientId(@PathVariable clientId: UUID): ResponseEntity<Client> =
        clientRepository.findById(clientId).map { ResponseEntity.ok(it) }
            .orElseThrow { IllegalArgumentException("Client not found: $clientId") }

    @PostMapping
    fun createClient(@RequestBody client: Client): ResponseEntity<Client> {
        return if (clientRepository.existsByEmail(client.email)) {
            ResponseEntity.badRequest().build()
        } else {
            val newClient = clientRepository.save(client)
            val uri = URI("/clients/${newClient.id}")
            ResponseEntity.created(uri).body(newClient)
        }
    }

    @PutMapping("/{clientId}")
    fun updateClient(@PathVariable clientId: UUID, @RequestBody updateClient: Client): ResponseEntity<Client> {
        return if (clientRepository.existsById(clientId)) {
            val client = clientRepository.findById(clientId).orElseThrow { IllegalArgumentException("") }
            val updatedClient = client.copy(name = updateClient.name, phoneNumber = updateClient.phoneNumber)
            ResponseEntity.ok(clientRepository.save(updatedClient))
        } else {
            ResponseEntity.notFound().build()
        }
    }

}
