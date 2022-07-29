package br.com.starter.demo.repository

import br.com.starter.demo.entity.Example
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository: CrudRepository<Example, Long> {
        fun findAllByOrderByAddedAtDesc(): Iterable<Example>
}