package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.domain.entities.Client
import dev.trodrigues.algalogapi.infra.repositories.ClientRepository
import org.hibernate.ObjectNotFoundException
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
        clientRepository.findById(clientId).map { ResponseEntity.ok(it) }.orElseThrow { IllegalArgumentException("Client not found: $clientId") }

}
