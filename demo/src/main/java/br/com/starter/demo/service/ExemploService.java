package br.com.starter.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starter.demo.entity.Exemplo;
import br.com.starter.demo.exception.NotFoundBusinessException;
import br.com.starter.demo.repository.ExemploRepository;


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
