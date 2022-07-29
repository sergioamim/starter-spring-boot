package br.com.starter.demo.controller

import br.com.starter.demo.controller.dto.ExampleDTO
import br.com.starter.demo.service.ExampleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/example")
class ExampleController(
    private val service: ExampleService
) {
    fun findAll(): ResponseEntity<List<ExampleDTO>> {
        return ResponseEntity.ok(service.findAll())
    }
}