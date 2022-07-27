package br.com.dasa.startermodel.api.utils;

import java.util.Arrays;
import java.util.List;

import br.com.dasa.startermodel.controller.dto.ExemploDTO;
import br.com.dasa.startermodel.entity.Exemplo;

public class ExemploTestBuilder {

    public static List<Exemplo> getListEntities() {
        return Arrays.asList(getEntity(), getEntity() );
    }

    public static Exemplo getEntity() {
        final Exemplo obj = new Exemplo();
        obj.setId(1L);
        obj.setName("name");
        obj.setDescription("description");
        return obj;
    }


    public static ExemploDTO getDto() {
        return ExemploDTO.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
    }
}
