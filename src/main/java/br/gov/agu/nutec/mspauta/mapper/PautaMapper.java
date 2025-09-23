package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import br.gov.agu.nutec.mspauta.enums.StatusAnalise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    @Mapping(source = "orgaoJulgador.nome", target = "orgaoJulgador")
    @Mapping(source = "turno.descricao", target = "turno")
    @Mapping(source = "statusAnaliseComparecimento", target = "analiseComparecimento")
    @Mapping(source = "audiencias", target = "audiencias")
    PautaResponseDTO toResponseDTO(PautaEntity entity);

    @Mapping(source = "horario", target = "hora")
    @Mapping(source = "advogados", target = "advogados", qualifiedByName = "mapAdvogadosToNames")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapPrioridadeToString")
    AudienciaResponseDTO mapAudienciaToDTO(AudienciaEntity audiencia);

    @Named("mapAdvogadosToNames")
    default List<String> mapAdvogadosToNames(List<AdvogadoEntity> advogados) {
        if (advogados == null) {
            return null;
        }
        return advogados.stream()
                .map(AdvogadoEntity::getNome)
                .collect(Collectors.toList());
    }

    @Named("mapPrioridadeToString")
    default String mapPrioridadeToString(br.gov.agu.nutec.mspauta.enums.Prioridade prioridade) {
        return prioridade != null ? prioridade.name() : null;
    }
    
    default String mapStatusAnaliseComparecimento(StatusAnalise status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }
}
