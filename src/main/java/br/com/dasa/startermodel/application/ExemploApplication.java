package br.com.dasa.startermodel.application;

import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.service.ExemploService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExemploApplication {

    @Autowired
    private ExemploService exemploService;

    public List<Exemplo> getAll() {
        return exemploService.getAll();
    }
}
