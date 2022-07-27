package br.com.starter.demo.controller.mapper;

import br.com.starter.demo.controller.dto.ExemploDTO;
import br.com.starter.demo.entity.Exemplo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-27T20:27:02-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Amazon.com Inc.)"
)
@Component
public class ExemploMapperImpl implements ExemploMapper {

    @Override
    public Exemplo toEntity(ExemploDTO source) {
        if ( source == null ) {
            return null;
        }

        Exemplo.ExemploBuilder exemplo = Exemplo.builder();

        exemplo.id( source.getId() );
        exemplo.name( source.getName() );
        exemplo.description( source.getDescription() );

        return exemplo.build();
    }

    @Override
    public ExemploDTO toDTO(Exemplo destination) {
        if ( destination == null ) {
            return null;
        }

        ExemploDTO.ExemploDTOBuilder exemploDTO = ExemploDTO.builder();

        exemploDTO.id( destination.getId() );
        exemploDTO.name( destination.getName() );
        exemploDTO.description( destination.getDescription() );

        return exemploDTO.build();
    }

    @Override
    public List<ExemploDTO> asDTOList(List<Exemplo> source) {
        if ( source == null ) {
            return null;
        }

        List<ExemploDTO> list = new ArrayList<ExemploDTO>( source.size() );
        for ( Exemplo exemplo : source ) {
            list.add( toDTO( exemplo ) );
        }

        return list;
    }
}
