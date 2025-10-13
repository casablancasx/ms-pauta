package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.OrgaoJulgadorResponse;
import br.gov.agu.nutec.mspauta.entity.OrgaoJulgadorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrgaoJulgadorMapper {


    OrgaoJulgadorResponse mapToResponse(OrgaoJulgadorEntity entity);
}
