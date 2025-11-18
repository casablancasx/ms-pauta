package br.gov.agu.nutec.mspauta.mapper;

import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
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
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PautaMapper {

    /**
     * Converte uma entidade PautaEntity para PautaResponseDTO
     * 
     * @param entity A entidade PautaEntity a ser convertida
     * @return PautaResponseDTO contendo os dados convertidos
     */
    @Mapping(source = "orgaoJulgador.nome", target = "orgaoJulgador")
    @Mapping(source = "turno", target = "turno", qualifiedByName = "mapTurnoToString")
    @Mapping(source = "audiencias", target = "audiencias")
    @Mapping(source = "sala.nome", target = "sala")
    @Mapping(source = "escala.pautista.nome", target = "pautista")
    PautaResponseDTO toResponseDTO(PautaEntity entity);

    /**
     * Converte uma entidade AudienciaEntity para AudienciaResponseDTO
     * 
     * @param audiencia A entidade AudienciaEntity a ser convertida
     * @return AudienciaResponseDTO contendo os dados convertidos
     */
    @Mapping(target = "hora", ignore = true)
    @Mapping(source = "numeroProcesso", target = "numeroProcesso")
    @Mapping(source = "classeJudicial.nome", target = "classeJudicial")
    @Mapping(source = "assunto.nome", target = "assunto")
    @Mapping(source = "nomeParte", target = "nomeParte")
    @Mapping(source = "advogados", target = "advogados", qualifiedByName = "mapAdvogadosToNames")
    @Mapping(target = "statusComparecimento", ignore = true)
    AudienciaResponseDTO mapAudienciaToDTO(AudienciaEntity audiencia);

    /**
     * Converte uma lista de AdvogadoEntity para uma lista de nomes de advogados
     * 
     * @param advogados Lista de entidades AdvogadoEntity
     * @return Lista de nomes de advogados
     */
    @Named("mapAdvogadosToNames")
    default List<String> mapAdvogadosToNames(List<AdvogadoEntity> advogados) {
        if (CollectionUtils.isEmpty(advogados)) {
            return Collections.emptyList();
        }
        return advogados.stream()
                .filter(advogado -> advogado != null && advogado.getNome() != null)
                .map(AdvogadoEntity::getNome)
                .collect(Collectors.toList());
    }
    
    /**
     * Converte o enum Turno para sua representação em String (descrição)
     * 
     * @param turno O enum Turno a ser convertido
     * @return Descrição do turno
     */
    @Named("mapTurnoToString")
    default String mapTurnoToString(Turno turno) {
        return turno != null ? turno.getDescricao() : null;
    }

    /**
     * Converte o enum StatusAnalise para sua representação em String (descrição)
     * 
     * @param status O enum StatusAnalise a ser convertido
     * @return Descrição do status de análise
     */
    @Named("mapStatusAnalise")
    default String mapStatusAnalise(AnaliseComparecimento status) {
        if (status == null) {
            return null;
        }
        return status.getDescricao();
    }
    
    /**
     * Converte o valor de prioridade para sua representação em String.
     * Trata tanto o caso de enum Prioridade quanto o caso da string direta.
     * 
     * @param prioridade O valor de prioridade a ser convertido
     * @return Representação em String da prioridade
     */
    @Named("mapPrioridadeToString")
    default String mapPrioridadeToString(String prioridade) {
        if (!StringUtils.hasText(prioridade)) {
            return null;
        }
        
        try {
            // Tenta converter para enum se for um valor válido do enum
            Prioridade enumPrioridade = Prioridade.valueOf(prioridade);
            // Retorna a descrição se tiver método getter, caso contrário o próprio nome
            try {
                return (String) Prioridade.class.getMethod("getDescricao").invoke(enumPrioridade);
            } catch (Exception e) {
                return prioridade;
            }
        } catch (IllegalArgumentException e) {
            // Se não for um valor de enum válido, retorna o próprio valor
            return prioridade;
        }
    }
}
