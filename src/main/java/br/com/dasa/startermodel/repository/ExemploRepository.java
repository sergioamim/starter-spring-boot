package br.com.dasa.startermodel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.dasa.startermodel.entity.Exemplo;

@Repository
public interface ExemploRepository extends CrudRepository<Exemplo, Long> {
    List<Exemplo> findAll();
}
