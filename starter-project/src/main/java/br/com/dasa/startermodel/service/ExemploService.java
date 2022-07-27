package br.com.dasa.startermodel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.exception.NotFoundBusinessException;
import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.repository.ExemploRepository;

@Service
public class ExemploService {

    @Autowired
    private ExemploRepository repository;

    public ExemploService(ExemploRepository repository) {
        this.repository = repository;
    }

    public Exemplo create(Exemplo exemplo) {
        return repository.save(exemplo);
    }

    public List<Exemplo> findAll() {
        return repository.findAll();
    }

    public Exemplo findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundBusinessException("Exemplo nao encontrado."));
    }

    public Exemplo update(Exemplo exemplo) {
        return repository.save(exemplo);
    }

    public void delete(Long id) {
        Exemplo exemplo = repository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException("Exemplo nao encontrado."));
        repository.delete(exemplo);
    }
}
