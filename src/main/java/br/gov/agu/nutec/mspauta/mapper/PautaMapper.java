package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaDetalhadaDTO;
import br.gov.agu.nutec.mspauta.dto.response.PautaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.AdvogadoEntity;
import br.gov.agu.nutec.mspauta.entity.AudienciaEntity;
import br.gov.agu.nutec.mspauta.entity.PautaEntity;
import br.gov.agu.nutec.mspauta.enums.Prioridade;
import br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento;
import br.gov.agu.nutec.mspauta.enums.Turno;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper responsável pela conversão entre entidades e DTOs relacionados à pauta.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {AudienciaMapper.class})
public interface PautaMapper {

    @Mapping(source = "orgaoJulgador.nome", target = "orgaoJulgador")
    @Mapping(source = "sala.nome", target = "sala")
    @Mapping(source = "turno", target = "turno", qualifiedByName = "mapTurno")
    @Mapping(source = "escala.pautista.nome", target = "pautista")
    @Mapping(source = "escala.avaliador.nome", target = "avaliador")
    PautaResponseDTO mapToResponse(PautaEntity pautaEntity);

    @Mapping(source = "orgaoJulgador.nome", target = "orgaoJulgador")
    @Mapping(source = "sala.nome", target = "sala")
    @Mapping(source = "turno", target = "turno", qualifiedByName = "mapTurno")
    @Mapping(source = "escala.pautista.nome", target = "pautista")
    @Mapping(source = "escala.avaliador.nome", target = "avaliador")
    @Mapping(source = "audiencias", target = "audiencias")
    PautaDetalhadaDTO mapToResponseComAudiencias(PautaEntity pautaEntity);

    @Named("mapTurno")
    default String mapTurno(Turno turno) {
        return turno != null ? turno.getDescricao() : null;
    }

}
