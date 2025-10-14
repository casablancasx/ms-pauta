package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.AssuntoResponse;
import br.gov.agu.nutec.mspauta.entity.AssuntoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssuntoMapper {


    @Mapping(target = "assunto", source = "nome")
    AssuntoResponse mapToResponse(AssuntoEntity entity);



}
