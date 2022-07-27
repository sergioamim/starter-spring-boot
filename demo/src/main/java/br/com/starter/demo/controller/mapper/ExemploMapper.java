package br.com.starter.demo.controller.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.starter.demo.controller.dto.ExemploDTO;
import br.com.starter.demo.entity.Exemplo;

@Mapper(componentModel = "spring")
public interface ExemploMapper {

    ExemploMapper INSTANCE = Mappers.getMapper( ExemploMapper.class );

    Exemplo toEntity(ExemploDTO source);
    ExemploDTO toDTO(Exemplo destination);

    List<ExemploDTO> asDTOList(List<Exemplo> source);

}
