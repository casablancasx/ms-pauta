package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.UfResponse;
import br.gov.agu.nutec.mspauta.entity.UfEntity;
import br.gov.agu.nutec.mspauta.enums.Uf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UfMapper {


    @Mapping(target = "sigla", source = "sigla", qualifiedByName = "mapUfToString")
    UfResponse mapToResponse(UfEntity ufEntity);



    @Named( "mapUfToString")
    default String mapUfToString(Uf uf){
        return uf.name();
    }
}
