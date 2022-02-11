package br.com.dasa.startermodel.repository;

import br.com.dasa.startermodel.entity.Exemplo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @autor: Jucilene L. Mahle
 **/

@Repository
public interface ExemploRepository {

    default List<Exemplo> getAll(){
        List<Exemplo> exemploList = new ArrayList<>();
        exemploList.add(Exemplo.builder().firstName("Starter").lastName("Model 1").build());
        exemploList.add(Exemplo.builder().firstName("Starter").lastName("Model 2").build());

        return exemploList;
    }
}
