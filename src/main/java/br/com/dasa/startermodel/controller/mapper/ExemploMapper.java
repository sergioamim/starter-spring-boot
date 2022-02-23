package br.com.dasa.startermodel.controller.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.dasa.startermodel.controller.dto.ExemploDTO;
import br.com.dasa.startermodel.entity.Exemplo;

@Mapper(componentModel = "spring")
public interface ExemploMapper {

    ExemploMapper INSTANCE = Mappers.getMapper( ExemploMapper.class );

    Exemplo toEntity(ExemploDTO source);
    ExemploDTO toDTO(Exemplo destination);

    List<ExemploDTO> asDTOList(List<Exemplo> source);

}
