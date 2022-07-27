package br.com.starter.demo.api.utils;

import java.util.Arrays;
import java.util.List;

import br.com.starter.demo.controller.dto.ExemploDTO;
import br.com.starter.demo.entity.Exemplo;

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
