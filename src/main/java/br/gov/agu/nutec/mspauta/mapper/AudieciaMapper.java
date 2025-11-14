package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AudieciaMapper {

    @Mapping(source = "audienciaId", target = "audienciaId")
    @Mapping(source = "numeroProcesso", target = "numeroProcesso")
    @Mapping(source = "horario", target = "hora")
    @Mapping(source = "nomeParte", target = "nomeParte")
    @Mapping(source = "advogados", target = "advogados", qualifiedByName = "mapAdvogadosToNames")
    @Mapping(source = "assunto.nome", target = "assunto")
    @Mapping(source = "classeJudicial.nome", target = "classeJudicial")
    @Mapping(source = "prioritario", target = "prioritaria")
    @Mapping(source = "analiseComparecimento", target = "statusComparecimento", qualifiedByName = "mapStatusAnalise")
    @Mapping(source = "analise", target = "analise")
    AudienciaResponseDTO mapToResponse(AudienciaEntity audienciaEntity);

    @Named("mapAdvogadosToNames")
    default List<String> mapAdvogadosToNames(List<AdvogadoEntity> advogados) {
        if (CollectionUtils.isEmpty(advogados)) {
            return Collections.emptyList();
        }
        return advogados.stream()
                .filter(a -> a != null && a.getNome() != null)
                .map(AdvogadoEntity::getNome)
                .collect(Collectors.toList());
    }

    @Named("mapStatusAnalise")
    default String mapStatusAnalise(AnaliseComparecimento status) {
        return status != null ? status.getDescricao() : null;
    }
}
