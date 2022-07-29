package br.com.starter.demo.service

import br.com.starter.demo.controller.dto.ExampleDTO

interface ExampleService {
    fun findAll(): List<ExampleDTO>
}
