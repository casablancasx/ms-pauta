package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.SalaResponse;
import br.gov.agu.nutec.mspauta.entity.SalaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalaMapper {


    SalaResponse mapToResponse(SalaEntity salaEntity);
}
