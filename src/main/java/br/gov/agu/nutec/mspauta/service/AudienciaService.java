package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.dto.request.AudienciaUpdateDTO;
import br.gov.agu.nutec.mspauta.dto.response.AudienciaResponseDTO;
import br.gov.agu.nutec.mspauta.entity.*;
import br.gov.agu.nutec.mspauta.repository.AudienciaRepository;
import br.gov.agu.nutec.mspauta.mapper.AudienciaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.gov.agu.nutec.mspauta.enums.AnaliseComparecimento.ANALISE_PENDENTE;
import static br.gov.agu.nutec.mspauta.enums.StatusCadastroTarefa.PENDENTE;

@Service
@RequiredArgsConstructor
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;
    private final AdvogadoService advogadoService;
    private final ClasseJudicialService classeJudicialService;
    private final AssuntoService assuntoService;
    private final AudienciaMapper audienciaMapper;

    @Transactional
    public void criarAudiencias(List<AudienciaDTO> audiencias, PautaEntity pauta) {
        Set<String> nomesAdvogados = audiencias.stream()
                .flatMap(a -> a.advogados().stream())
                .collect(Collectors.toSet());

        Map<String, AdvogadoEntity> advogadosPorNome = advogadoService.ensureAdvogadosByNames(nomesAdvogados);


        ClasseJudicialEntity classeJudicial = classeJudicialService.buscarClassePorNome(audiencias.getFirst().classeJudicial());

        AssuntoEntity assunto = assuntoService.buscarAssunto(audiencias.getFirst().assunto());

        List<AudienciaEntity> entidades = audiencias.stream()
                .map(a -> {
                    List<AdvogadoEntity> advogados = a.advogados().stream()
                            .map(advogadosPorNome::get)
                            .toList();

                    // Define a prioridade da audiência com base nos advogados ou no DTO
                    boolean prioridade = advogados.stream()
                            .anyMatch(AdvogadoEntity::isPrioritario);

                    return new AudienciaEntity(
                            a.cnj(),
                            classeJudicial,
                            assunto,
                            a.poloAtivo(),
                            a.hora(),
                            advogados,
                            prioridade,
                            PENDENTE,
                            pauta,
                            PENDENTE,
                            ANALISE_PENDENTE,
                            a.tipoContestacao()
                    );
                })
                .toList();

        audienciaRepository.saveAll(entidades);
    }


    public AudienciaResponseDTO atualizaAudiencia(AudienciaUpdateDTO audiencia) {

        AudienciaEntity audienciaOptional = audienciaRepository.findById(audiencia.audienciaId()).orElseThrow(
                () -> new RuntimeException("audiencia nao encontrada")
        );

        if (audiencia.analiseComparecimento() != null ){
            audienciaOptional.setAnaliseComparecimento(audiencia.analiseComparecimento());
        }

        if (audiencia.observacao() != null){
            audienciaOptional.setAnalise(audiencia.observacao());
        }

        audienciaOptional = audienciaRepository.save(audienciaOptional);
        return audienciaMapper.toResponse(audienciaOptional);

    }
}
