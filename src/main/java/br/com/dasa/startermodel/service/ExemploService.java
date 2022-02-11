package br.com.dasa.startermodel.service;

import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.repository.ExemploRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor: Jucilene L. Mahle
 **/

@Service
@RequiredArgsConstructor
public class ExemploService {

    @Autowired
    private ExemploRepository exemploRepository;

    public List<Exemplo> getAll() {
        return exemploRepository.getAll();

    }
}
