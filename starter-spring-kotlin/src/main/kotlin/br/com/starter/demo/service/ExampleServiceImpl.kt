package br.com.starter.demo.service

import br.com.starter.demo.repository.ExampleRepository
import org.springframework.stereotype.Service

@Service
class ExampleServiceImpl: ExampleService

    override fun findAll( repository: ExampleRepository) {
        return repository.findAll()
    }
}

