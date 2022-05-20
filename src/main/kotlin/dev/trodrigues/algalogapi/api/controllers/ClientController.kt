package dev.trodrigues.algalogapi.api.controllers

import dev.trodrigues.algalogapi.domain.entities.Client
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/clients")
class ClientController {

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE])
    fun list(): List<Client> =
        listOf(Client(UUID.randomUUID(), "Thiago Rodrigues", "hey@trodrigues.dev", "(54) 99999-9999"))

}
