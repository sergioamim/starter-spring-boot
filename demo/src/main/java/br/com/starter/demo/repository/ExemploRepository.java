package br.com.starter.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.starter.demo.entity.Exemplo;

@Repository
public interface ExemploRepository extends CrudRepository<Exemplo, Long> {
    List<Exemplo> findAll();
}
