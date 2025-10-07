package br.gov.agu.nutec.mspauta.service;

import br.gov.agu.nutec.mspauta.dto.AudienciaDTO;
import br.gov.agu.nutec.mspauta.entity.*;
import br.gov.agu.nutec.mspauta.repository.AudienciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.gov.agu.nutec.mspauta.enums.StatusAnaliseComparecimento.ANALISE_PENDENTE;
import static br.gov.agu.nutec.mspauta.enums.StatusCadastro.PENDENTE;

@Service
@RequiredArgsConstructor
public class AudienciaService {

    private final AudienciaRepository audienciaRepository;
    private final AdvogadoService advogadoService;
    private final ClasseJudicialService classeJudicialService;
    private final AssuntoService assuntoService;

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
                            ANALISE_PENDENTE
                    );
                })
                .toList();

        audienciaRepository.saveAll(entidades);
    }
}
