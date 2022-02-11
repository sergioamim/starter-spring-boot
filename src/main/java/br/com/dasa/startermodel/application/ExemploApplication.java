package br.com.dasa.startermodel.application;

import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.service.ExemploService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor: Jucilene L. Mahle
 **/
@Service
@Slf4j
public class ExemploApplication {

    @Autowired
    private ExemploService exemploService;

    public List<Exemplo> getAll() {

        return exemploService.getAll();

    }
}
