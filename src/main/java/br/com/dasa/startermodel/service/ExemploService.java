package br.com.dasa.startermodel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dasa.startermodel.entity.Exemplo;

@Service
public class ExemploService {

    public List<Exemplo> getAll() {

        List<Exemplo> exemploList = new ArrayList();
        exemploList.add(Exemplo.builder().firstName("Starter").lastName("Model 1").build());
        exemploList.add(Exemplo.builder().firstName("Starter").lastName("Model 2").build());

        return exemploList;

    }
}
