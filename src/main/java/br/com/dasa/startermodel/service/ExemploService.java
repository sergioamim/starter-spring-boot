package br.com.dasa.startermodel.service;

import br.com.dasa.startermodel.entity.Exemplo;
import br.com.dasa.startermodel.repository.ExemploRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExemploService {

    public List<Exemplo> getAll() {

        List<Exemplo> exemploList = new ArrayList<>();
        exemploList.add(Exemplo.builder().firstName("Starter").lastName("Model 1").build());
        exemploList.add(Exemplo.builder().firstName("Starter").lastName("Model 2").build());

        return exemploList;

    }
}
